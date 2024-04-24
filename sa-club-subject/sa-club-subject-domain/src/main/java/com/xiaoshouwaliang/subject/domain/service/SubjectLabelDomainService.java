package com.xiaoshouwaliang.subject.domain.service;

import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-19
 */
public interface SubjectLabelDomainService {
    /**
     * 添加标签
     * @param subjectLabelBO
     */

    void addLabel(SubjectLabelBO subjectLabelBO);

    boolean updateLabel(SubjectLabelBO subjectLabelBO);

    /**
     * 删除标签
     * @param subjectLabelBO
     * @return
     */

    boolean deleteLabel(SubjectLabelBO subjectLabelBO);

    /**
     * 根据分类id查询所有标签
     * @param subjectLabelBO
     * @return
     */
    List<SubjectLabelBO> queryLabel(SubjectLabelBO subjectLabelBO);
}
