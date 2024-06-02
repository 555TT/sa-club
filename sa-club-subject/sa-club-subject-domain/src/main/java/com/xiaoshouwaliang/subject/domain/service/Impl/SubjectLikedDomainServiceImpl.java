package com.xiaoshouwaliang.subject.domain.service.Impl;
import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.subject.common.entity.PageResult;
import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.common.enums.LikedEnum;
import com.xiaoshouwaliang.subject.domain.converter.SubjectLikeConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLikedBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLikedMessage;
import com.xiaoshouwaliang.subject.domain.redis.RedisUtil;
import com.xiaoshouwaliang.subject.domain.service.SubjectLikedDomainService;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectInfo;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLiked;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectInfoService;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectLikedService;
import org.apache.commons.collections4.MapUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 小手WA凉
 * @date 2024-05-12
 */
@Service
public class SubjectLikedDomainServiceImpl implements SubjectLikedDomainService {
    private static final String SUBJECT_LIKED_KEY = "subject.liked";

    private static final String SUBJECT_LIKED_COUNT_KEY = "subject.liked.count";

    private static final String SUBJECT_LIKED_DETAIL_KEY = "subject.liked.detail";
    @Resource
    private SubjectLikedService subjectLikedService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Override
    public void addLike(SubjectLikedBO subjectLikedBO) {
        Long subjectId = subjectLikedBO.getSubjectId();
        String likeUserId = subjectLikedBO.getLikeUserId();
        Integer status = subjectLikedBO.getStatus();
//        String hashKey=subjectId.toString()+":"+likeUserId;
//        redisUtil.putHash(SUBJECT_LIKED_KEY,hashKey,status.toString());
        SubjectLikedMessage subjectLikedMessage = new SubjectLikedMessage();
        subjectLikedMessage.setSubjectId(subjectId);
        subjectLikedMessage.setLikeUserId(likeUserId);
        subjectLikedMessage.setStatus(status);
        rocketMQTemplate.convertAndSend("subject-liked", JSON.toJSONString(subjectLikedMessage));

        String countKey=SUBJECT_LIKED_COUNT_KEY+"."+subjectId;
        String detailKey=SUBJECT_LIKED_DETAIL_KEY+"."+subjectId+"."+likeUserId;
        if(LikedEnum.LIKED.code==status){
            redisUtil.increment(countKey,1);
            redisUtil.set(detailKey,"1");
        }else {
            Integer count = redisUtil.getInt(countKey);
            if(count==null||count<=0)
                return;
            redisUtil.del(detailKey);
            redisUtil.increment(countKey,-1);
        }
    }

    @Override
    public Boolean isLiked(Long subjectId, String userName) {
        String detailKey=SUBJECT_LIKED_DETAIL_KEY+"."+subjectId+"."+userName;
        return redisUtil.exist(detailKey);
    }

    @Override
    public Integer subjectLikedCount(Long subjectId) {
        String countKey=SUBJECT_LIKED_COUNT_KEY+"."+subjectId;
        Integer count = redisUtil.getInt(countKey);
        if(count==null||count<=0){
            return 0;
        }
        return count;
    }

    @Override
    public void syncLiked() {
        Map<Object, Object> subjectLikedMap = redisUtil.getHashAndDelete(SUBJECT_LIKED_KEY);
        if(MapUtils.isEmpty(subjectLikedMap)){
            return;
        }
        LinkedList<SubjectLiked> subjectLikedList = new LinkedList<>();
        subjectLikedMap.forEach((key,val)->{
            SubjectLiked subjectLiked = new SubjectLiked();
            String[] split = key.toString().split(":");
            String subjectId = split[0];
            String userName = split[1];
            subjectLiked.setSubjectId(Long.valueOf(subjectId));
            subjectLiked.setLikeUserId(userName);
            subjectLiked.setStatus(Integer.valueOf(val.toString()));
            subjectLiked.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
            subjectLikedList.add(subjectLiked);
        });
        subjectLikedService.batchInsert(subjectLikedList);
    }

    @Override
    public PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO) {
        PageResult<SubjectLikedBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectLikedBO.getPageNo());
        pageResult.setPageSize(subjectLikedBO.getPageSize());
        int start=(subjectLikedBO.getPageNo()-1)*subjectLikedBO.getPageSize();
        SubjectLiked subjectLiked = SubjectLikeConverter.INSTANCE.converterBOTOPOJO(subjectLikedBO);
        int count=subjectLikedService.countByCondition(subjectLiked);
        if(count==0){
            return pageResult;
        }
        List<SubjectLiked> likes =subjectLikedService.queryPage(subjectLiked,start,subjectLikedBO.getPageSize());
        List<SubjectLikedBO> subjectLikedBOS = SubjectLikeConverter.INSTANCE.convertListInfoToBO(likes);
        subjectLikedBOS.forEach(like->{
            SubjectInfo subjectInfo = subjectInfoService.queryById(like.getSubjectId());
            like.setSubjectName(subjectInfo.getSubjectName());
        });
        pageResult.setRecords(subjectLikedBOS);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public void syncLikedMsg(SubjectLikedBO subjectLikedBO) {
        SubjectLiked subjectLiked = new SubjectLiked();
        subjectLiked.setSubjectId(Long.valueOf(subjectLikedBO.getSubjectId()));
        subjectLiked.setLikeUserId(subjectLikedBO.getLikeUserId());
        subjectLiked.setStatus(subjectLikedBO.getStatus());
        subjectLiked.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectLikedService.insert(subjectLiked);
    }
}
