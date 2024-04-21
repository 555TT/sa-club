package com.xiaoshouwaliang.subject.domain.handler;

import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.common.enums.SubjectInfoTypeEnum;
import com.xiaoshouwaliang.subject.domain.converter.SubjectRadioConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectAnswerBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectOptionBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectRadio;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-20
 */
@Service
public class RadioTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectAnswerBO> optionList = subjectInfoBO.getOptionList();//得到题目信息的每个选项信息
        LinkedList<SubjectRadio> radios = new LinkedList<>();
        optionList.forEach(option->{
            SubjectRadio subjectRadio = SubjectRadioConverter.INSTANCE.converterBoToRadio(option);
            subjectRadio.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            radios.add(subjectRadio);
        });
        subjectRadioService.batchInsert(radios);
    }

    @Override
    public SubjectOptionBO queryAnswer(Long subjectId ) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId(subjectId);
        List<SubjectRadio> result = subjectRadioService.queryByCondition(subjectRadio);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectRadioConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
