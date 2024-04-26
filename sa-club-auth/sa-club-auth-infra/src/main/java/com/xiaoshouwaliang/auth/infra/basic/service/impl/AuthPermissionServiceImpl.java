package com.xiaoshouwaliang.auth.infra.basic.service.impl;

import com.xiaoshouwaliang.auth.infra.basic.entity.AuthPermission;
import com.xiaoshouwaliang.auth.infra.basic.dao.AuthPermissionDao;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthPermissionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * (AuthPermission)表服务实现类
 *
 * @author makejava
 * @since 2024-04-25 15:41:10
 */
@Service("authPermissionService")
public class AuthPermissionServiceImpl implements AuthPermissionService {
    @Resource
    private AuthPermissionDao authPermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthPermission queryById(Long id) {
        return this.authPermissionDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    @Override
    public AuthPermission insert(AuthPermission authPermission) {
        this.authPermissionDao.insert(authPermission);
        return authPermission;
    }

    /**
     * 修改数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(AuthPermission authPermission) {
        return this.authPermissionDao.update(authPermission)>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authPermissionDao.deleteById(id) > 0;
    }
}
