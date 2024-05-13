package com.xiaoshouwaliang.subject.infra.basic.service.impl;

import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLiked;
import com.xiaoshouwaliang.subject.infra.basic.mapper.SubjectLikedDao;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectLikedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目点赞表(SubjectLiked)表服务实现类
 *
 * @author makejava
 * @since 2024-05-12 15:08:42
 */
@Service("subjectLikedService")
public class SubjectLikedServiceImpl implements SubjectLikedService {
    @Resource
    private SubjectLikedDao subjectLikedDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectLiked queryById(Long id) {
        return this.subjectLikedDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param subjectLiked 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectLiked insert(SubjectLiked subjectLiked) {
        this.subjectLikedDao.insert(subjectLiked);
        return subjectLiked;
    }

    /**
     * 修改数据
     *
     * @param subjectLiked 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectLiked update(SubjectLiked subjectLiked) {
        this.subjectLikedDao.update(subjectLiked);
        return this.queryById(subjectLiked.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectLikedDao.deleteById(id) > 0;
    }

    @Override
    public void batchInsert(LinkedList<SubjectLiked> subjectLikedList) {
        subjectLikedDao.insertBatch(subjectLikedList);
    }

    @Override
    public List<SubjectLiked> queryByCondition(SubjectLiked subjectLiked) {
        return subjectLikedDao.queryByCondition(subjectLiked);
    }

    @Override
    public  int countByCondition(SubjectLiked subjectLiked) {
        return (int) subjectLikedDao.count(subjectLiked);
    }

    @Override
    public List<SubjectLiked> queryPage(SubjectLiked subjectLiked, int start, Integer pageSize) {
        return subjectLikedDao.queryPage(subjectLiked,start,pageSize);
    }

}
