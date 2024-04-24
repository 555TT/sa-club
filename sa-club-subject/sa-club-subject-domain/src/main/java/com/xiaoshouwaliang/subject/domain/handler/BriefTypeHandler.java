package com.xiaoshouwaliang.subject.domain.handler;

import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.common.enums.SubjectInfoTypeEnum;
import com.xiaoshouwaliang.subject.domain.converter.SubjectBriefConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectAnswerBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectOptionBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectBrief;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectJudge;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 简答题处理类
 * @author 小手WA凉
 * @date 2024-04-20
 */
@Service
public class BriefTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = SubjectBriefConverter.INSTANCE.converterBoToBrief(subjectInfoBO);
        subjectBrief.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO queryAnswer(Long subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        SubjectBrief result=subjectBriefService.queryByCondition(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;
    }
}
