package com.xiaoshouwaliang.subject.domain.converter;

import com.xiaoshouwaliang.subject.domain.entity.SubjectAnswerBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectJudge;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-27T20:30:40+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_321 (Oracle Corporation)"
)
public class SubjectJudgeConverterImpl implements SubjectJudgeConverter {

    @Override
    public SubjectAnswerBO convertPOJOToAnswerBO(SubjectJudge result) {
        if ( result == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setIsCorrect( result.getIsCorrect() );

        return subjectAnswerBO;
    }
}
