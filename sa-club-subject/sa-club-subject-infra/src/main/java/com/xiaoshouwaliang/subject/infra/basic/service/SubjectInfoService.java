package com.xiaoshouwaliang.subject.infra.basic.service;

import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectInfo;

import java.util.List;

/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author makejava
 * @since 2024-04-19 21:12:24
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    int queryCount(SubjectInfo subjectInfo,Long categoryId,Long labelId);

    List<SubjectInfo> queryPage(SubjectInfo subjectInfo, Long categoryId, Long labelId, int start, Integer pageSize);

    List<SubjectInfo> getContributeList();

    /**
     * 上一题下一题
     * @param subjectId
     * @param categoryId
     * @param labelId
     * @param i 1上一题 0下一题
     * @return
     */
    Long querySubjectIdCursor(Long subjectId, Long categoryId, Long labelId, int i);
}
