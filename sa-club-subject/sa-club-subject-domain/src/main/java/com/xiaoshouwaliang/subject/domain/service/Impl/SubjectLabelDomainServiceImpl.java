package com.xiaoshouwaliang.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.domain.converter.SubjectLabelConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;
import com.xiaoshouwaliang.subject.domain.service.SubjectLabelDomainService;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectCategory;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLabel;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectCategoryService;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

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
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.addLabel.POJO:{}", JSON.toJSONString(subjectLabel));
        }
        //如果是一级分类则查出所有标签
        SubjectCategory subjectCategory = subjectCategoryService.queryById(subjectLabelBO.getCategoryId());
        if(1==subjectCategory.getCategoryType()){//一级分类的categoryType为1
            //查出该一级分类下的所有标签
            //查询一级分类下的所有二级分类
            SubjectCategory subjectCategory1 = new SubjectCategory();
            subjectCategory1.setParentId(subjectCategory.getId());
            List<SubjectCategory> categoryList = subjectCategoryService.queryCategory(subjectCategory1);
            List<SubjectLabelBO> list=new LinkedList<>();
            SubjectLabel subjectLabel1 = new SubjectLabel();
            categoryList.forEach(category->{
                subjectLabel1.setCategoryId(category.getId());
                subjectCategory1.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
                List<SubjectLabel> subjectLabels = subjectLabelService.queryByCategoryId(subjectLabel1);
                List<SubjectLabelBO> list1 = SubjectLabelConverter.INSTANCE.converterPOListToBOList(subjectLabels);
                list.addAll(list1);
            });
            return list;
        }
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectLabel> list=subjectLabelService.queryByCategoryId(subjectLabel);
        return SubjectLabelConverter.INSTANCE.converterPOListToBOList(list);
    }
}
