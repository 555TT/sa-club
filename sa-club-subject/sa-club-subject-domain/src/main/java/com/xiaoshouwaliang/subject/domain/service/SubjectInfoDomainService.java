package com.xiaoshouwaliang.subject.domain.service;

import com.xiaoshouwaliang.subject.common.entity.PageResult;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectInfoEs;

/**
 * @author 小手WA凉
 * @date 2024-04-20
 */
public interface SubjectInfoDomainService {

    /**
     * 新增题目
     * @param subjectInfoBO
     */
    void addSubject(SubjectInfoBO subjectInfoBO);


    PageResult<SubjectInfoBO> querySubjectPage(SubjectInfoBO subjectInfoBO);


    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);

    /**
     * 全文检索
     * @param subjectInfoBO
     * @return
     */
    PageResult<SubjectInfoEs> getSubjectPageBySearch(SubjectInfoBO subjectInfoBO);
}
