package com.xiaoshouwaliang.subject.application.controller.converter;

import com.xiaoshouwaliang.subject.application.controller.dto.SubjectCategoryDTO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE=Mappers.getMapper(SubjectCategoryDTOConverter.class);
    SubjectCategoryBO converterDtoToCategoryBO(SubjectCategoryDTO subjectCategoryDTO);
    List<SubjectCategoryDTO> converterBOlistToDTOlist(List<SubjectCategoryBO> BOlist);


    SubjectCategoryDTO converterBOToDTO(SubjectCategoryBO subjectCategoryBO);


}
