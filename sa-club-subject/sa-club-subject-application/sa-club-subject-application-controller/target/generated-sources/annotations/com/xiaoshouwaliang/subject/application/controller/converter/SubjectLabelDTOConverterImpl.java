package com.xiaoshouwaliang.subject.application.controller.converter;

import com.xiaoshouwaliang.subject.application.controller.dto.SubjectLabelDTO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-21T18:00:07+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_321 (Oracle Corporation)"
)
public class SubjectLabelDTOConverterImpl implements SubjectLabelDTOConverter {

    @Override
    public SubjectLabelBO converterDtoToLabelBO(SubjectLabelDTO subjectLabelDTO) {
        if ( subjectLabelDTO == null ) {
            return null;
        }

        SubjectLabelBO subjectLabelBO = new SubjectLabelBO();

        subjectLabelBO.setId( subjectLabelDTO.getId() );
        subjectLabelBO.setLabelName( subjectLabelDTO.getLabelName() );
        subjectLabelBO.setSortNum( subjectLabelDTO.getSortNum() );
        subjectLabelBO.setCategoryId( subjectLabelDTO.getCategoryId() );
        subjectLabelBO.setIsDeleted( subjectLabelDTO.getIsDeleted() );

        return subjectLabelBO;
    }

    @Override
    public List<SubjectLabelDTO> converterBOlistToDTOlist(List<SubjectLabelBO> BOlist) {
        if ( BOlist == null ) {
            return null;
        }

        List<SubjectLabelDTO> list = new ArrayList<SubjectLabelDTO>( BOlist.size() );
        for ( SubjectLabelBO subjectLabelBO : BOlist ) {
            list.add( subjectLabelBOToSubjectLabelDTO( subjectLabelBO ) );
        }

        return list;
    }

    protected SubjectLabelDTO subjectLabelBOToSubjectLabelDTO(SubjectLabelBO subjectLabelBO) {
        if ( subjectLabelBO == null ) {
            return null;
        }

        SubjectLabelDTO subjectLabelDTO = new SubjectLabelDTO();

        subjectLabelDTO.setId( subjectLabelBO.getId() );
        subjectLabelDTO.setLabelName( subjectLabelBO.getLabelName() );
        subjectLabelDTO.setSortNum( subjectLabelBO.getSortNum() );
        subjectLabelDTO.setCategoryId( subjectLabelBO.getCategoryId() );
        subjectLabelDTO.setIsDeleted( subjectLabelBO.getIsDeleted() );

        return subjectLabelDTO;
    }
}
