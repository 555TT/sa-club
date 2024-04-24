package com.xiaoshouwaliang.subject.domain.handler;

import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.common.enums.SubjectInfoTypeEnum;
import com.xiaoshouwaliang.subject.domain.converter.SubjectJudgeConverter;
import com.xiaoshouwaliang.subject.domain.converter.SubjectMultipleConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectAnswerBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectOptionBO;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectJudge;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectMultiple;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectJudgeService;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 * 新增判断题处理类
 * @author 小手WA凉
 * @date 2024-04-20
 */
@Service
public class JudgeTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectJudgeService subjectJudgeService;
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    //add判断题
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectInfoBO.getOptionList().get(0).getIsCorrect());
        subjectJudgeService.insert(subjectJudge);
    }

    @Override
    public SubjectOptionBO queryAnswer(Long subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(subjectId);
        SubjectJudge result =subjectJudgeService.queryByCondition(subjectJudge);
        SubjectAnswerBO subjectAnswerBO =SubjectJudgeConverter.INSTANCE.convertPOJOToAnswerBO(result);
        List<SubjectAnswerBO> subjectAnswerBOList=new ArrayList<>();
        subjectAnswerBOList.add(subjectAnswerBO);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
