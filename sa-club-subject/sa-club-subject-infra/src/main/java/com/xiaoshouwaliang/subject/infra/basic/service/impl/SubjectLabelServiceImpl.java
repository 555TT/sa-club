package com.xiaoshouwaliang.subject.infra.basic.service.impl;

import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLabel;
import com.xiaoshouwaliang.subject.infra.basic.mapper.SubjectLabelDao;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目标签表(SubjectLabel)表服务实现类
 *
 * @author makejava
 * @since 2024-04-18 22:18:36
 */
@Service("subjectLabelService")
public class SubjectLabelServiceImpl implements SubjectLabelService {
    @Resource
    private SubjectLabelDao subjectLabelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectLabel queryById(Long id) {
        return this.subjectLabelDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(SubjectLabel subjectLabel) {
        this.subjectLabelDao.insert(subjectLabel);
    }

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectLabel subjectLabel) {
        return this.subjectLabelDao.update(subjectLabel);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectLabelDao.deleteById(id) > 0;
    }

    @Override
    public List<SubjectLabel> queryByCondition(SubjectLabel subjectLabel) {
        List<SubjectLabel> result =subjectLabelDao.queryByCondition(subjectLabel);
        return result;
    }

    @Override
    public List<SubjectLabel> batchQueryByIds(List<Long> labels) {
        return subjectLabelDao.batchQueryByIds(labels);
    }
}
