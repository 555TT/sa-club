package com.xiaoshouwaliang.subject.application.controller.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签表(SubjectLabel)DTO
 */
@Data
public class SubjectLabelDTO implements Serializable {
/**
     * 主键
     */
    private Long id;
/**
     * 标签分类
     */
    private String labelName;
/**
     * 排序
     */
    private Integer sortNum;

    private Long categoryId;


    private Integer isDeleted;
}

