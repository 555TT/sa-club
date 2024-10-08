package com.xiaoshouwaliang.subject.domain.service;

import com.xiaoshouwaliang.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
public interface SubjectCategoryDomainService {
    /**
     * 新增分类
     */
    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询分类（查一级分类、二级分类共用）
     * @param subjectCategoryBO
     * @return
     */

    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 更新分类
     * @param subjectCategoryBO
     * @return
     */
    Boolean updateCategory(SubjectCategoryBO subjectCategoryBO);

    Boolean deleteCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 一次性查询大分类下所有小分类及小分类下的所有标签
     * @param categoryId
     * @return
     */
    List<SubjectCategoryBO> queryCategoryAndLabel(Long categoryId);
}
