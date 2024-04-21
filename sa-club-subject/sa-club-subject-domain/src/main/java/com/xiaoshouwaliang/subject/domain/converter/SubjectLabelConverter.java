package com.xiaoshouwaliang.subject.domain.converter;

import com.xiaoshouwaliang.subject.domain.entity.SubjectCategoryBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectCategory;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
@Mapper
public interface SubjectLabelConverter {
    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);
    SubjectLabel converterBoToLabel(SubjectLabelBO subjectLabelBO);
    //List<POJO>->List<BO>
    List<SubjectLabelBO> converterPOListToBOList(List<SubjectLabel> labelList);
}
