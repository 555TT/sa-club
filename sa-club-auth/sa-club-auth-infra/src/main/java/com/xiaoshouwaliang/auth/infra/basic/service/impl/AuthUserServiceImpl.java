package com.xiaoshouwaliang.auth.infra.basic.service.impl;

import com.xiaoshouwaliang.auth.infra.basic.entity.AuthUser;
import com.xiaoshouwaliang.auth.infra.basic.dao.AuthUserDao;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 用户信息表(AuthUser)表服务实现类
 *
 * @author makejava
 * @since 2024-04-25 15:38:22
 */
@Service("authUserService")
public class AuthUserServiceImpl implements AuthUserService {
    @Resource
    private AuthUserDao authUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthUser queryById(Long id) {
        return this.authUserDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    @Override
    public AuthUser insert(AuthUser authUser) {
        this.authUserDao.insert(authUser);
        return authUser;
    }

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 实例对象
     */
    @Override
    public int update(AuthUser authUser) {
        return this.authUserDao.update(authUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authUserDao.deleteById(id) > 0;
    }

    @Override
    public AuthUser queryByUserName(AuthUser authUser) {
        return authUserDao.queryByUserName(authUser);
    }
}
