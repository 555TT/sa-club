package com.xiaoshouwaliang.subject.application.controller.dto;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class SubjectCategoryDTO implements Serializable {
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
     * 分类下的题目数量
     */
    private Integer count;
    /**
     * 该分类下的标签
     */
    List<SubjectLabelDTO> labelDTOList;
}


