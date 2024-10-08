package com.xiaoshouwaliang.subject.application.controller.dto;

import com.xiaoshouwaliang.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)DTO
 */
@Data
public class SubjectInfoDTO extends PageInfo implements Serializable {
/**
     * 主键
     */
    private Long id;
/**
     * 题目名称
     */
    private String subjectName;
/**
     * 题目难度
     */
    private Integer subjectDifficult;
/**
     * 出题人名
     */
    private String settleName;
/**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
/**
     * 题目分数
     */
    private Integer subjectScore;
/**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 题目答案
     */
    private String subjectAnswer;
    //分类ids
    private List<Long> categoryIds;
    //标签ids
    private List<Long> labelIds;
    //选项
    private List<SubjectAnswerBO> optionList;



    //标签id
    private Long labelId;
    //分类id
    private Long categoryId;
    /**
     * 标签name
     */
    private List<String> labelName;
    //全文检索的keyWord
    private String keyWord;

    /**
     * 出题人昵称
     */
    private String createUser;

    /**
     * 出题人头像
     */
    private String createUserAvatar;
    /**
     * 出的题目数量（排行榜）
     */
    private Integer subjectCount;

    /**
     * 当前用户是否点赞当前题目
     */
    private Boolean liked;
    /**
     * 当前题目的点赞数量
     */
    private Integer likedCount;
    /**
     * 下一题
     */
    private Long nextSubjectId;

    /**
     * 上一题
     */
    private Long lastSubjectId;
}

