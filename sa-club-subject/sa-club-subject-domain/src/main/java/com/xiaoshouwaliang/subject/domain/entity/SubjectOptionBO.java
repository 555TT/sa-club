package com.xiaoshouwaliang.subject.domain.entity;

import lombok.Data;

import java.util.List;

/**题目答案
 * @author 小手WA凉
 * @date 2024-04-21
 */
@Data
public class SubjectOptionBO {
    /**
     * 简答题答案
     */
    private String subjectAnswer;
    /**
     * 选择题和判断题答案
     */
    private List<SubjectAnswerBO> optionList;
}
