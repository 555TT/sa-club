package com.xiaoshouwaliang.subject.infra.basic.service;

import com.xiaoshouwaliang.subject.common.entity.PageResult;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectInfoEs;

/**
 * @author 小手WA凉
 * @date 2024-05-10
 */
public interface SubjectEsService {

    boolean insert(SubjectInfoEs subjectInfoEs);

    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);
}
