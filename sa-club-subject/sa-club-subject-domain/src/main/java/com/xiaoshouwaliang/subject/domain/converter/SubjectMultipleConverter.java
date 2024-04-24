package com.xiaoshouwaliang.subject.domain.converter;

import com.xiaoshouwaliang.subject.domain.entity.SubjectAnswerBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectMultiple;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
@Mapper
public interface SubjectMultipleConverter {
    SubjectMultipleConverter INSTANCE = Mappers.getMapper(SubjectMultipleConverter.class);
    SubjectMultiple converterBoToMultiple(SubjectAnswerBO subjectAnswerBO);
    List<SubjectAnswerBO> convertEntityToBoList(List<SubjectMultiple> subjectMultipleList);
}
