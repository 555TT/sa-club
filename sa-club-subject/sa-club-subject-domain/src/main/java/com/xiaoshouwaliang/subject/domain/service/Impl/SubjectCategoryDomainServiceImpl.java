package com.xiaoshouwaliang.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.domain.converter.SubjectCategoryConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectCategoryBO;
import com.xiaoshouwaliang.subject.domain.service.SubjectCategoryDomainService;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectCategory;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    @Resource
    private SubjectCategoryService subjectCategoryService;
    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.converterBoToCategory(subjectCategoryBO);
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.add.POJO:{}", JSON.toJSONString(subjectCategory));
        }
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.converterBoToCategory(subjectCategoryBO);
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.queryCategory.POJO:{}", JSON.toJSONString(subjectCategory));
        }
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> categoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOS = SubjectCategoryConverter.INSTANCE.converterPOListToBOList(categoryList);
        return subjectCategoryBOS;
    }

    @Override
    public Boolean updateCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.converterBoToCategory(subjectCategoryBO);
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.updateCategory.POJO:{}", JSON.toJSONString(subjectCategory));
        }
        int update = subjectCategoryService.update(subjectCategory);
        return update>0;
    }

    @Override
    public Boolean deleteCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.converterBoToCategory(subjectCategoryBO);
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryDomainServiceImpl.updateCategory.POJO:{}", JSON.toJSONString(subjectCategory));
        }
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectCategoryService.update(subjectCategory)>0;
    }
}
