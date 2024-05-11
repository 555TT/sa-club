package com.xiaoshouwaliang.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.subject.common.entity.PageResult;
import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.common.util.IdWorkerUtil;
import com.xiaoshouwaliang.subject.common.util.LoginUtil;
import com.xiaoshouwaliang.subject.domain.converter.SubjectInfoConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectOptionBO;
import com.xiaoshouwaliang.subject.domain.handler.SubjectTypeHandler;
import com.xiaoshouwaliang.subject.domain.handler.SubjectTypeHandlerFactory;
import com.xiaoshouwaliang.subject.domain.redis.RedisUtil;
import com.xiaoshouwaliang.subject.domain.service.SubjectInfoDomainService;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectInfo;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectInfoEs;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLabel;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectMapping;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectEsService;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectInfoService;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectLabelService;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectMappingService;
import com.xiaoshouwaliang.subject.infra.entity.UserInfo;
import com.xiaoshouwaliang.subject.infra.rpc.UserRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 小手WA凉
 * @date 2024-04-20
 */

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {
    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private SubjectEsService subjectEsService;
    @Resource
    private UserRpc userRpc;
    @Resource
    private RedisUtil redisUtil;

    private final String RANK_KEY="rank_Key";
    @Transactional
    @Override
    public void addSubject(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }
        //插入基本题目信息表
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToPOJO(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);//先插入基本信息表，之后subject中就有主键值了
        //插入具体题目表（单选、多选、判断、简答）
        Integer subjectType = subjectInfoBO.getSubjectType();
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectType);
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);
        //插入关系映射表
        List<Long> categoryIds = subjectInfoBO.getCategoryIds();
        List<Long> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> subjectMappings = new ArrayList<>();
        for (Long categoryId : categoryIds) {
            for (Long labelId : labelIds) {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(categoryId);
                subjectMapping.setLabelId(labelId);
                subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
                subjectMappings.add(subjectMapping);
            }
        }
        subjectMappingService.batchInsert(subjectMappings);
        //同步到es
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setDocId(new IdWorkerUtil(1,1,1).nextId());
        subjectInfoEs.setSubjectId(subjectInfo.getId());
        subjectInfoEs.setSubjectAnswer(subjectInfoBO.getSubjectAnswer());
        subjectInfoEs.setSubjectName(subjectInfo.getSubjectName());
        subjectInfoEs.setSubjectType(subjectInfo.getSubjectType());
        subjectInfoEs.setCreateTime(new Date().getTime());
        subjectInfoEs.setCreateUser("小手WA凉");
        subjectEsService.insert(subjectInfoEs);
        //同步到redis排行榜
        redisUtil.addScore(RANK_KEY, LoginUtil.getLoginId(),1);
    }

    @Override
    public PageResult<SubjectInfoBO> querySubjectPage(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }
        //返回结果pageResult
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        //查询返回数量
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToPOJO(subjectInfoBO);
        int count = subjectInfoService.queryCount(subjectInfo, subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId());
        if (count == 0)
            return pageResult;
        //查询返回记录
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId(), subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConverter.INSTANCE.convertListInfoToBO(subjectInfoList);
        //组装返回结果pageResult
        pageResult.setRecords(subjectInfoBOS);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }
        //查询题目基本信息
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        //查询具体题目的答案
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        SubjectOptionBO subjectOptionBO = handler.queryAnswer(subjectInfoBO.getId());
        //将具体答案组装到返回结果中
        SubjectInfoBO resultBo = SubjectInfoConverter.INSTANCE.convertPOJOToBO(subjectInfo,subjectOptionBO);
        //将题目的标签s组装到返回结果中
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfoBO.getId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        List<SubjectMapping> mappingList= subjectMappingService.queryByCondition(subjectMapping);
        List<Long> labels=mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> resultLabels=subjectLabelService.batchQueryByIds(labels);
        List<String> labelNames=resultLabels.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        resultBo.setLabelName(labelNames);
        return resultBo;
    }

    @Override
    public PageResult<SubjectInfoEs> getSubjectPageBySearch(SubjectInfoBO subjectInfoBO) {
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setKeyWord(subjectInfoBO.getKeyWord());
        subjectInfoEs.setPageNo(subjectInfoBO.getPageNo());
        subjectInfoEs.setPageSize(subjectInfoBO.getPageSize());
        return subjectEsService.querySubjectList(subjectInfoEs);
    }

    @Override
    public List<SubjectInfoBO> getContributeList() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtil.rankWithScore(RANK_KEY, 0, 5);
        if (log.isInfoEnabled()) {
            log.info("getContributeList.typedTuples:{}", JSON.toJSONString(typedTuples));
        }
        if(CollectionUtils.isEmpty(typedTuples)){
            return Collections.emptyList();
        }
        LinkedList<SubjectInfoBO> result = new LinkedList<>();
        typedTuples.forEach(rank->{
            UserInfo userInfo = userRpc.getUserInfo(rank.getValue());
            SubjectInfoBO subjectInfoBO = new SubjectInfoBO();
            subjectInfoBO.setCreateUserAvatar(userInfo.getAvatar());//出题人头像
            subjectInfoBO.setCreateUser(userInfo.getNickName());//昵称
            subjectInfoBO.setSubjectCount(rank.getScore().intValue());//出题数量
            result.add(subjectInfoBO);
        });
        return result;
    }
}
