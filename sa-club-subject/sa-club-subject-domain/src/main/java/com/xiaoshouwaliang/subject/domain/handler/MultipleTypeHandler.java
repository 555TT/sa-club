package com.xiaoshouwaliang.subject.domain.handler;

import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.common.enums.SubjectInfoTypeEnum;
import com.xiaoshouwaliang.subject.domain.converter.SubjectMultipleConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectAnswerBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectOptionBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectMultiple;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-20
 */
@Service
public class MultipleTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectMultipleService subjectMultipleService;
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    //add多选题
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectAnswerBO> optionList = subjectInfoBO.getOptionList();
        List<SubjectMultiple> subjectMultiples=new ArrayList<>();
        optionList.forEach(option->{
            SubjectMultiple subjectMultiple =SubjectMultipleConverter.INSTANCE.converterBoToMultiple(option);
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiples.add(subjectMultiple);
        });
        subjectMultipleService.batchInsert(subjectMultiples);
    }

    @Override
    public SubjectOptionBO queryAnswer(Long subjectId ) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(subjectId);
        List<SubjectMultiple> result = subjectMultipleService.queryByCondition(subjectMultiple);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectMultipleConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
