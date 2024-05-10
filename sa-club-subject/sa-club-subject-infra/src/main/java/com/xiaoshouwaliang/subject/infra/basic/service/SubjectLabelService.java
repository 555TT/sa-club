package com.xiaoshouwaliang.subject.infra.basic.service;

import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLabel;

import java.util.List;

/**
 * 题目标签表(SubjectLabel)表服务接口
 *
 * @author makejava
 * @since 2024-04-18 22:18:36
 */
public interface SubjectLabelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLabel queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    void insert(SubjectLabel subjectLabel);

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int update(SubjectLabel subjectLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    //根据分类id查询所有标签
    List<SubjectLabel> queryByCondition(SubjectLabel subjectLabel);

    /**
     * 根据批量id查询标签
     * @param labels
     * @return
     */

    List<SubjectLabel> batchQueryByIds(List<Long> labels);
}
