package com.xiaoshouwaliang.practice.server.dao;

import com.xiaoshouwaliang.practice.server.entity.po.SubjectJudgePO;

public interface SubjectJudgeDao {


    SubjectJudgePO selectBySubjectId(Long repeatSubjectId);


}