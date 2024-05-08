package com.xiaoshouwaliang.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.domain.converter.SubjectCategoryConverter;
import com.xiaoshouwaliang.subject.domain.converter.SubjectLabelConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectCategoryBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;
import com.xiaoshouwaliang.subject.domain.service.SubjectCategoryDomainService;
import com.xiaoshouwaliang.subject.domain.util.CacheUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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
    @Resource
    private CacheUtil cacheUtil;

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
            Integer count = subjectMappingService.querySubjectCount(bo.getId());
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
        String cacheKey="categoryAndLabel."+categoryId;
        List<SubjectCategoryBO> result = cacheUtil.getResult(cacheKey, SubjectCategoryBO.class, key -> getSubjectCategoryBOS(subjectCategory));
        return result;
    }

    private List<SubjectCategoryBO> getSubjectCategoryBOS(SubjectCategory subjectCategory) {
        List<SubjectCategory> categoryList = subjectCategoryService.queryCategory(subjectCategory);
        //将属于每个小分类的所有标签组装到小分类中
        List<SubjectCategoryBO> subjectCategoryBOS = SubjectCategoryConverter.INSTANCE.converterPOListToBOList(categoryList);
        List<CompletableFuture<Map<Long, List<SubjectLabelBO>>>> completableFutureList = subjectCategoryBOS.stream().map(category ->
                CompletableFuture.supplyAsync(() ->
                                getLabelsForCategory(category)
                        , labelThreadPool)).collect(Collectors.toList());
        //装着categoryId和List<SubjectLabelBO>的map
        Map<Long,List<SubjectLabelBO>> map=new HashMap<>();
        completableFutureList.forEach(completableFuture -> {
            try {
                Map<Long, List<SubjectLabelBO>> longListMap = completableFuture.get();
                if(!CollectionUtils.isEmpty(longListMap)){
                    map.putAll(longListMap);
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

        });
        subjectCategoryBOS.forEach(subjectCategoryBO -> {
            List<SubjectLabelBO> list = map.get(subjectCategoryBO.getId());
            if (!CollectionUtils.isEmpty(list)){
                subjectCategoryBO.setLabelBOList(list);
            }
        });
        return subjectCategoryBOS;
    }

    /**
     *
     * @param categoryBO
     * @return Map<分类id，该分类下的所有标签>
     */

    private Map<Long, List<SubjectLabelBO>> getLabelsForCategory(SubjectCategoryBO categoryBO) {
        //先在mapping中查到labelId，在到label表中根据id查到labelName
        Map<Long, List<SubjectLabelBO>> map=new HashMap<>();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryBO.getId());
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        List<SubjectMapping> subjectMappings = subjectMappingService.queryByCondition(subjectMapping);
        if(CollectionUtils.isEmpty(subjectMappings))
            return map;
        List<SubjectLabel> subjectLabels = subjectLabelService.batchQueryByIds(subjectMappings.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList()));
        List<SubjectLabelBO> list = SubjectLabelConverter.INSTANCE.converterPOListToBOList(subjectLabels);
        map.put(categoryBO.getId(),list);
        return map;
    }
}
