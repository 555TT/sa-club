package com.xiaoshouwaliang.practice.server.dao;

import com.xiaoshouwaliang.practice.server.entity.dto.CategoryDTO;
import com.xiaoshouwaliang.practice.server.entity.po.CategoryPO;
import com.xiaoshouwaliang.practice.server.entity.po.PrimaryCategoryPO;

import java.util.List;

/**
 * 题目分类(SubjectCategory)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-17 15:10:03
 */
public interface SubjectCategoryDao {

    List<PrimaryCategoryPO> getPrimaryCategory(CategoryDTO categoryDTO);

    CategoryPO selectById(Long parentId);

    List<CategoryPO> selectList(CategoryDTO categoryDTOTemp);
}