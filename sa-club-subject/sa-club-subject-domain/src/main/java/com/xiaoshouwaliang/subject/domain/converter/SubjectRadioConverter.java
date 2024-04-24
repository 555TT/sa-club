package com.xiaoshouwaliang.subject.domain.converter;

import com.xiaoshouwaliang.subject.domain.entity.SubjectAnswerBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLabel;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
@Mapper
public interface SubjectRadioConverter {
    SubjectRadioConverter INSTANCE = Mappers.getMapper(SubjectRadioConverter.class);
    SubjectRadio converterBoToRadio(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertEntityToBoList(List<SubjectRadio> result);
}
