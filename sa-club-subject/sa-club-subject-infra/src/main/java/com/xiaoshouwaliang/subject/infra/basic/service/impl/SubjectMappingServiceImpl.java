package com.xiaoshouwaliang.subject.infra.basic.service.impl;

import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectMapping;
import com.xiaoshouwaliang.subject.infra.basic.mapper.SubjectMappingDao;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务实现类
 *
 * @author makejava
 * @since 2024-04-19 20:32:17
 */
@Service("subjectMappingService")
public class SubjectMappingServiceImpl implements SubjectMappingService {
    @Resource
    private SubjectMappingDao subjectMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectMapping queryById(Long id) {
        return this.subjectMappingDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMapping insert(SubjectMapping subjectMapping) {
        this.subjectMappingDao.insert(subjectMapping);
        return subjectMapping;
    }

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMapping update(SubjectMapping subjectMapping) {
        this.subjectMappingDao.update(subjectMapping);
        return this.queryById(subjectMapping.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectMappingDao.deleteById(id) > 0;
    }

    @Override
    public void batchInsert(List<SubjectMapping> subjectMappings) {
        subjectMappingDao.insertBatch(subjectMappings);
    }

    @Override
    public List<SubjectMapping> queryByCondition(SubjectMapping subjectMapping) {
        return subjectMappingDao.queryByCondition(subjectMapping);
    }

    @Override
    public Integer querySubjectCount(Long categoryId) {
        return subjectMappingDao.querySubjectCount(categoryId);
    }
}
