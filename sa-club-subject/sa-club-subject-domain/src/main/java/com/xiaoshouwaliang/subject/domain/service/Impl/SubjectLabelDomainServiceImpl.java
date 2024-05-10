package com.xiaoshouwaliang.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.domain.converter.SubjectLabelConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;
import com.xiaoshouwaliang.subject.domain.service.SubjectLabelDomainService;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectCategory;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLabel;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectMapping;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectCategoryService;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectLabelService;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 小手WA凉
 * @date 2024-04-19
 */
@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private SubjectCategoryService subjectCategoryService;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Override
    public void addLabel(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.converterBoToLabel(subjectLabelBO);
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.addLabel.POJO:{}", JSON.toJSONString(subjectLabel));
        }
        subjectLabel.setIsDeleted(0);
        subjectLabelService.insert(subjectLabel);
    }

    @Override
    public boolean updateLabel(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.converterBoToLabel(subjectLabelBO);
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.addLabel.POJO:{}", JSON.toJSONString(subjectLabel));
        }
        return subjectLabelService.update(subjectLabel)>0;
    }

    @Override
    public boolean deleteLabel(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.converterBoToLabel(subjectLabelBO);
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.addLabel.POJO:{}", JSON.toJSONString(subjectLabel));
        }
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectLabelService.update(subjectLabel)>0;
    }

    @Override
    public List<SubjectLabelBO> queryLabel(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.converterBoToLabel(subjectLabelBO);
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.addLabel.POJO:{}", JSON.toJSONString(subjectLabel));
        }
        //如果是一级分类则查出所有标签
        SubjectCategory subjectCategory = subjectCategoryService.queryById(subjectLabelBO.getCategoryId());
        if (1 == subjectCategory.getCategoryType()) {//一级分类的categoryType为1
            //查出该一级分类下的所有标签
            SubjectLabel label = new SubjectLabel();
            label.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
            label.setCategoryId(subjectCategory.getId());
            List<SubjectLabel> subjectLabels = subjectLabelService.queryByCondition(label);
            List<SubjectLabelBO> list = SubjectLabelConverter.INSTANCE.converterPOListToBOList(subjectLabels);
            return list;
        }
        //否则为二级分类
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryByCondition(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return Collections.emptyList();
        }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryByIds(labelIdList);
        List<SubjectLabelBO> boList = new LinkedList<>();
        labelList.forEach(label -> {
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setId(label.getId());
            bo.setLabelName(label.getLabelName());
            bo.setCategoryId(categoryId);
            bo.setSortNum(label.getSortNum());
            boList.add(bo);
        });
        return boList;
    }
}
