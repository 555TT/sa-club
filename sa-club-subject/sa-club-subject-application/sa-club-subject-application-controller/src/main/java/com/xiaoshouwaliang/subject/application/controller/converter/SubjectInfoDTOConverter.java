package com.xiaoshouwaliang.subject.application.controller.converter;

import com.xiaoshouwaliang.subject.application.controller.dto.SubjectInfoDTO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
@Mapper
public interface SubjectInfoDTOConverter {
    SubjectInfoDTOConverter INSTANCE=Mappers.getMapper(SubjectInfoDTOConverter.class);
    SubjectInfoBO converterDtoToSubjectBO(SubjectInfoDTO subjectInfoDTO);
    SubjectInfoDTO converterBOToSubjectDTO(SubjectInfoBO subjectInfoBO);


}
