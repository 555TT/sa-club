package com.xiaoshouwaliang.practice.server.dao;

import com.xiaoshouwaliang.practice.server.entity.po.SubjectRadioPO;

import java.util.List;

public interface SubjectRadioDao {

    /**
     * 根据题目id查询单选题目
     */
    List<SubjectRadioPO> selectBySubjectId(Long subjectId);

}
