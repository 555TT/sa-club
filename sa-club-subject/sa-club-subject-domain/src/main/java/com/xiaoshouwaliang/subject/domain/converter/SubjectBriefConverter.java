package com.xiaoshouwaliang.subject.domain.converter;

import com.xiaoshouwaliang.subject.domain.entity.SubjectAnswerBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectBrief;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
@Mapper
public interface SubjectBriefConverter {
    SubjectBriefConverter INSTANCE = Mappers.getMapper(SubjectBriefConverter.class);
    SubjectBrief converterBoToBrief(SubjectInfoBO subjectInfoBO);
}
