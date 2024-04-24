package com.xiaoshouwaliang.subject.domain.converter;

import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLabel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-23T19:00:28+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_321 (Oracle Corporation)"
)
public class SubjectLabelConverterImpl implements SubjectLabelConverter {

    @Override
    public SubjectLabel converterBoToLabel(SubjectLabelBO subjectLabelBO) {
        if ( subjectLabelBO == null ) {
            return null;
        }

        SubjectLabel subjectLabel = new SubjectLabel();

        subjectLabel.setId( subjectLabelBO.getId() );
        subjectLabel.setLabelName( subjectLabelBO.getLabelName() );
        subjectLabel.setSortNum( subjectLabelBO.getSortNum() );
        subjectLabel.setCategoryId( subjectLabelBO.getCategoryId() );
        subjectLabel.setIsDeleted( subjectLabelBO.getIsDeleted() );

        return subjectLabel;
    }

    @Override
    public List<SubjectLabelBO> converterPOListToBOList(List<SubjectLabel> labelList) {
        if ( labelList == null ) {
            return null;
        }

        List<SubjectLabelBO> list = new ArrayList<SubjectLabelBO>( labelList.size() );
        for ( SubjectLabel subjectLabel : labelList ) {
            list.add( subjectLabelToSubjectLabelBO( subjectLabel ) );
        }

        return list;
    }

    protected SubjectLabelBO subjectLabelToSubjectLabelBO(SubjectLabel subjectLabel) {
        if ( subjectLabel == null ) {
            return null;
        }

        SubjectLabelBO subjectLabelBO = new SubjectLabelBO();

        subjectLabelBO.setId( subjectLabel.getId() );
        subjectLabelBO.setLabelName( subjectLabel.getLabelName() );
        subjectLabelBO.setSortNum( subjectLabel.getSortNum() );
        subjectLabelBO.setCategoryId( subjectLabel.getCategoryId() );
        subjectLabelBO.setIsDeleted( subjectLabel.getIsDeleted() );

        return subjectLabelBO;
    }
}
