package com.xiaoshouwaliang.subject.application.controller.converter;

import com.xiaoshouwaliang.subject.application.controller.dto.SubjectInfoDTO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectAnswerBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-21T18:00:07+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_321 (Oracle Corporation)"
)
public class SubjectInfoDTOConverterImpl implements SubjectInfoDTOConverter {

    @Override
    public SubjectInfoBO converterDtoToSubjectBO(SubjectInfoDTO subjectInfoDTO) {
        if ( subjectInfoDTO == null ) {
            return null;
        }

        SubjectInfoBO subjectInfoBO = new SubjectInfoBO();

        subjectInfoBO.setId( subjectInfoDTO.getId() );
        subjectInfoBO.setSubjectName( subjectInfoDTO.getSubjectName() );
        subjectInfoBO.setSubjectDifficult( subjectInfoDTO.getSubjectDifficult() );
        subjectInfoBO.setSettleName( subjectInfoDTO.getSettleName() );
        subjectInfoBO.setSubjectType( subjectInfoDTO.getSubjectType() );
        subjectInfoBO.setSubjectScore( subjectInfoDTO.getSubjectScore() );
        subjectInfoBO.setSubjectParse( subjectInfoDTO.getSubjectParse() );
        subjectInfoBO.setSubjectAnswer( subjectInfoDTO.getSubjectAnswer() );
        List<Long> list = subjectInfoDTO.getCategoryIds();
        if ( list != null ) {
            subjectInfoBO.setCategoryIds( new ArrayList<Long>( list ) );
        }
        List<Long> list1 = subjectInfoDTO.getLabelIds();
        if ( list1 != null ) {
            subjectInfoBO.setLabelIds( new ArrayList<Long>( list1 ) );
        }
        subjectInfoBO.setOptionList( subjectAnswerBOListToSubjectAnswerBOList( subjectInfoDTO.getOptionList() ) );
        subjectInfoBO.setLabelId( subjectInfoDTO.getLabelId() );
        subjectInfoBO.setCategoryId( subjectInfoDTO.getCategoryId() );
        List<String> list3 = subjectInfoDTO.getLabelName();
        if ( list3 != null ) {
            subjectInfoBO.setLabelName( new ArrayList<String>( list3 ) );
        }

        return subjectInfoBO;
    }

    @Override
    public SubjectInfoDTO converterBOToSubjectDTO(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectInfoDTO subjectInfoDTO = new SubjectInfoDTO();

        subjectInfoDTO.setId( subjectInfoBO.getId() );
        subjectInfoDTO.setSubjectName( subjectInfoBO.getSubjectName() );
        subjectInfoDTO.setSubjectDifficult( subjectInfoBO.getSubjectDifficult() );
        subjectInfoDTO.setSettleName( subjectInfoBO.getSettleName() );
        subjectInfoDTO.setSubjectType( subjectInfoBO.getSubjectType() );
        subjectInfoDTO.setSubjectScore( subjectInfoBO.getSubjectScore() );
        subjectInfoDTO.setSubjectParse( subjectInfoBO.getSubjectParse() );
        subjectInfoDTO.setSubjectAnswer( subjectInfoBO.getSubjectAnswer() );
        List<Long> list = subjectInfoBO.getCategoryIds();
        if ( list != null ) {
            subjectInfoDTO.setCategoryIds( new ArrayList<Long>( list ) );
        }
        List<Long> list1 = subjectInfoBO.getLabelIds();
        if ( list1 != null ) {
            subjectInfoDTO.setLabelIds( new ArrayList<Long>( list1 ) );
        }
        subjectInfoDTO.setOptionList( subjectAnswerBOListToSubjectAnswerBOList1( subjectInfoBO.getOptionList() ) );
        subjectInfoDTO.setLabelId( subjectInfoBO.getLabelId() );
        subjectInfoDTO.setCategoryId( subjectInfoBO.getCategoryId() );
        List<String> list3 = subjectInfoBO.getLabelName();
        if ( list3 != null ) {
            subjectInfoDTO.setLabelName( new ArrayList<String>( list3 ) );
        }

        return subjectInfoDTO;
    }

    protected SubjectAnswerBO subjectAnswerBOToSubjectAnswerBO(com.xiaoshouwaliang.subject.application.controller.dto.SubjectAnswerBO subjectAnswerBO) {
        if ( subjectAnswerBO == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO1 = new SubjectAnswerBO();

        subjectAnswerBO1.setOptionType( subjectAnswerBO.getOptionType() );
        subjectAnswerBO1.setOptionContent( subjectAnswerBO.getOptionContent() );
        subjectAnswerBO1.setIsCorrect( subjectAnswerBO.getIsCorrect() );

        return subjectAnswerBO1;
    }

    protected List<SubjectAnswerBO> subjectAnswerBOListToSubjectAnswerBOList(List<com.xiaoshouwaliang.subject.application.controller.dto.SubjectAnswerBO> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAnswerBO> list1 = new ArrayList<SubjectAnswerBO>( list.size() );
        for ( com.xiaoshouwaliang.subject.application.controller.dto.SubjectAnswerBO subjectAnswerBO : list ) {
            list1.add( subjectAnswerBOToSubjectAnswerBO( subjectAnswerBO ) );
        }

        return list1;
    }

    protected com.xiaoshouwaliang.subject.application.controller.dto.SubjectAnswerBO subjectAnswerBOToSubjectAnswerBO1(SubjectAnswerBO subjectAnswerBO) {
        if ( subjectAnswerBO == null ) {
            return null;
        }

        com.xiaoshouwaliang.subject.application.controller.dto.SubjectAnswerBO subjectAnswerBO1 = new com.xiaoshouwaliang.subject.application.controller.dto.SubjectAnswerBO();

        subjectAnswerBO1.setOptionType( subjectAnswerBO.getOptionType() );
        subjectAnswerBO1.setOptionContent( subjectAnswerBO.getOptionContent() );
        subjectAnswerBO1.setIsCorrect( subjectAnswerBO.getIsCorrect() );

        return subjectAnswerBO1;
    }

    protected List<com.xiaoshouwaliang.subject.application.controller.dto.SubjectAnswerBO> subjectAnswerBOListToSubjectAnswerBOList1(List<SubjectAnswerBO> list) {
        if ( list == null ) {
            return null;
        }

        List<com.xiaoshouwaliang.subject.application.controller.dto.SubjectAnswerBO> list1 = new ArrayList<com.xiaoshouwaliang.subject.application.controller.dto.SubjectAnswerBO>( list.size() );
        for ( SubjectAnswerBO subjectAnswerBO : list ) {
            list1.add( subjectAnswerBOToSubjectAnswerBO1( subjectAnswerBO ) );
        }

        return list1;
    }
}
