package com.xiaoshouwaliang.practice.server.service;

import com.xiaoshouwaliang.practice.api.common.PageResult;
import com.xiaoshouwaliang.practice.api.req.GetPracticeSubjectsReq;
import com.xiaoshouwaliang.practice.api.req.GetUnCompletePracticeReq;
import com.xiaoshouwaliang.practice.api.vo.*;
import com.xiaoshouwaliang.practice.server.entity.dto.PracticeSetDTO;
import com.xiaoshouwaliang.practice.server.entity.dto.PracticeSubjectDTO;

import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-06-02
 */
public interface PracticeSetService {

    List<SpecialPracticeVO> getSpecialPracticeContent();

    PracticeSetVO addPractice(PracticeSubjectDTO dto);

    PracticeSubjectListVO getSubjects(GetPracticeSubjectsReq req);

    PracticeSubjectVO getPracticeSubject(PracticeSubjectDTO dto);

    PageResult<PracticeSetVO> getPreSetContent(PracticeSetDTO dto);

    PageResult<UnCompletePracticeSetVO> getUnCompletePractice(GetUnCompletePracticeReq req);
}
