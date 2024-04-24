package com.xiaoshouwaliang.subject.application.controller.converter;

import com.xiaoshouwaliang.subject.application.controller.dto.SubjectCategoryDTO;
import com.xiaoshouwaliang.subject.application.controller.dto.SubjectLabelDTO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectCategoryBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
@Mapper
public interface SubjectLabelDTOConverter {
    SubjectLabelDTOConverter INSTANCE=Mappers.getMapper(SubjectLabelDTOConverter.class);
    SubjectLabelBO converterDtoToLabelBO(SubjectLabelDTO subjectLabelDTO);
    List<SubjectLabelDTO> converterBOlistToDTOlist(List<SubjectLabelBO> BOlist);
}
