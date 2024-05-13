package com.xiaoshouwaliang.subject.application.controller.converter;

import com.xiaoshouwaliang.subject.application.controller.dto.SubjectLikedDTO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLikedBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 小手WA凉
 * @date 2024-05-12
 */
@Mapper
public interface SubjectLikedDTOConverter {
    SubjectLikedDTOConverter INSTANCE= Mappers.getMapper(SubjectLikedDTOConverter.class);
    SubjectLikedBO converterDTOTOBO(SubjectLikedDTO subjectLikedDTO);
}
