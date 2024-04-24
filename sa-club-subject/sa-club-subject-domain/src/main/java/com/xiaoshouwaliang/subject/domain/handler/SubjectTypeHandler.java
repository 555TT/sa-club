package com.xiaoshouwaliang.subject.domain.handler;

import com.xiaoshouwaliang.subject.common.enums.SubjectInfoTypeEnum;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectOptionBO;

/**
 * @author 小手WA凉
 * @date 2024-04-20
 */
public interface SubjectTypeHandler{
    //得到处理类型枚举
    SubjectInfoTypeEnum getHandlerType();
    //实际题目的插入
    void add(SubjectInfoBO subjectInfoBO);
    //查询实际题目的答案

    SubjectOptionBO queryAnswer(Long subjectId);

}
