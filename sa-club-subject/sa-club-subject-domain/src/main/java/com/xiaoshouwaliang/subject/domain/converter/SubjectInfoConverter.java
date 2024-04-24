package com.xiaoshouwaliang.subject.domain.converter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectOptionBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);
    SubjectInfo convertBoToPOJO(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> convertListInfoToBO(List<SubjectInfo> subjectInfoList);
    SubjectInfoBO convertPOJOToBO(SubjectInfo subjectInfo, SubjectOptionBO subjectOptionBO);
}
