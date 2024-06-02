package com.xiaoshouwaliang.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 小手WA凉
 * @date 2024-06-02
 */
@Data
public class SubjectLikedMessage implements Serializable {


    /**
     * 题目id
     */
    private Long subjectId;

    /**
     * 点赞人id
     */
    private String likeUserId;

    /**
     * 点赞状态 1点赞 0不点赞
     */
    private Integer status;


}