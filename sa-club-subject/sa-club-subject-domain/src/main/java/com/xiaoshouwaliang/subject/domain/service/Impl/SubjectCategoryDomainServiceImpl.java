package com.xiaoshouwaliang.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.domain.converter.SubjectCategoryConverter;
import com.xiaoshouwaliang.subject.domain.converter.SubjectLabelConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectCategoryBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;
import com.xiaoshouwaliang.subject.domain.service.SubjectCategoryDomainService;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectCategory;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLabel;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectCategoryService;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectLabelService;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    @Resource
    private SubjectCategoryService subjectCategoryService;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private ThreadPoolExecutor labelThreadPool;

    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.converterBoToCategory(subjectCategoryBO);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.add.POJO:{}", JSON.toJSONString(subjectCategory));
        }
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.converterBoToCategory(subjectCategoryBO);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.queryCategory.POJO:{}", JSON.toJSONString(subjectCategory));
        }
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> categoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOS = SubjectCategoryConverter.INSTANCE.converterPOListToBOList(categoryList);
        subjectCategoryBOS.forEach(bo -> {
            Integer count = subjectMappingService.querySubjectCountByCategoryId(bo.getId());
            bo.setCount(count);
        });
        return subjectCategoryBOS;
    }

    @Override
    public Boolean updateCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.converterBoToCategory(subjectCategoryBO);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.updateCategory.POJO:{}", JSON.toJSONString(subjectCategory));
        }
        int update = subjectCategoryService.update(subjectCategory);
        return update > 0;
    }

    @Override
    public Boolean deleteCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.converterBoToCategory(subjectCategoryBO);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.updateCategory.POJO:{}", JSON.toJSONString(subjectCategory));
        }
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectCategoryService.update(subjectCategory) > 0;
    }

    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(Long categoryId){
        //查询大分类下的所有小分类
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(categoryId);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        List<SubjectCategory> categoryList = subjectCategoryService.queryCategory(subjectCategory);
        //将属于每个小分类的所有标签组装到小分类中
        List<SubjectCategoryBO> subjectCategoryBOS = SubjectCategoryConverter.INSTANCE.converterPOListToBOList(categoryList);
        List<FutureTask<Map<Long, List<SubjectLabelBO>>>> futureTaskList = new ArrayList<>();
        subjectCategoryBOS.forEach(category -> {
            FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask = new FutureTask<>(() ->
                    getLabelsForCategory(category));
            labelThreadPool.submit(futureTask);
            futureTaskList.add(futureTask);
        });
        Map<Long,List<SubjectLabelBO>> map=new HashMap<>();
        futureTaskList.forEach(futureTask -> {

            try {
                Map<Long, List<SubjectLabelBO>> longListMap = futureTask.get();
                map.putAll(longListMap);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        });
        subjectCategoryBOS.forEach(subjectCategoryBO -> {
            subjectCategoryBO.setLabelBOList(map.get(subjectCategoryBO.getId()));
        });
        return subjectCategoryBOS;
    }

    private Map<Long, List<SubjectLabelBO>> getLabelsForCategory(SubjectCategoryBO categoryBO) {
        Map<Long, List<SubjectLabelBO>> map=new HashMap<>();
        SubjectLabel subjectLabel = new SubjectLabel();
        subjectLabel.setCategoryId(categoryBO.getId());
        List<SubjectLabel> subjectLabels = subjectLabelService.queryByCategoryId(subjectLabel);
        List<SubjectLabelBO> list = SubjectLabelConverter.INSTANCE.converterPOListToBOList(subjectLabels);
        map.put(categoryBO.getId(),list);
        return map;
    }
}
