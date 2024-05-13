package com.xiaoshouwaliang.subject.domain.converter;

import com.xiaoshouwaliang.subject.domain.entity.SubjectLikedBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLiked;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-05-12
 */
@Mapper
public interface SubjectLikeConverter {
    SubjectLikeConverter INSTANCE= Mappers.getMapper(SubjectLikeConverter.class);
    SubjectLiked converterBOTOPOJO(SubjectLikedBO subjectLikedBO);
    List<SubjectLikedBO> convertListInfoToBO(List<SubjectLiked> subjectLikedList);
}
