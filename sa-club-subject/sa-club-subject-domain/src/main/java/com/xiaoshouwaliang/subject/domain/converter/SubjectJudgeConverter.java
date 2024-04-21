package com.xiaoshouwaliang.subject.domain.converter;

import com.xiaoshouwaliang.subject.domain.entity.SubjectAnswerBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectJudge;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
@Mapper
public interface SubjectJudgeConverter {
    SubjectJudgeConverter INSTANCE = Mappers.getMapper(SubjectJudgeConverter.class);

    SubjectAnswerBO convertPOJOToAnswerBO(SubjectJudge result);
}
