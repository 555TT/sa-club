package com.xiaoshouwaliang.subject.domain.entity;

import lombok.Data;

/**
 * @author 小手WA凉
 * @date 2024-04-20
 */
@Data
public class SubjectAnswerBO {
    //选项类型
    private Integer optionType;
    //选项类型
    private String optionContent;
    //该选项是否正确 1正确 0错误
    private Integer isCorrect;
}
