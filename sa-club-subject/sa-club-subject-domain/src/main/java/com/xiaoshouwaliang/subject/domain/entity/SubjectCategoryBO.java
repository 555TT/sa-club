package com.xiaoshouwaliang.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author makejava
 * @since 2024-04-17 15:10:08
 */
@Data
public class SubjectCategoryBO implements Serializable {
/**
     * 主键
     */
    private Long id;
/**
     * 分类名称
     */
    private String categoryName;
/**
     * 分类类型
     */
    private Integer categoryType;
/**
     * 图标连接
     */
    private String imageUrl;
/**
     * 父级id
     */
    private Long parentId;
/**
     * 是否删除 0: 未删除 1: 已删除
     */
    private Integer isDeleted;

    /**
     * 分类下的题目数量
     */
    private Integer count;
    /**
     * 该分类下的标签
     */
    List<SubjectLabelBO> labelBOList;
}

