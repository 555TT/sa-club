package com.xiaoshouwaliang.auth.infra.basic.service;

import com.xiaoshouwaliang.auth.infra.basic.entity.AuthUserRole;

import java.util.List;

/**
 * 用户角色表(AuthUserRole)表服务接口
 *
 * @author makejava
 * @since 2024-04-25 15:40:59
 */
public interface AuthUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUserRole queryById(Long id);


    /**
     * 新增数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    AuthUserRole insert(AuthUserRole authUserRole);

    /**
     * 修改数据
     *
     * @param authUserRole 实例对象
     * @return 实例对象
     */
    AuthUserRole update(AuthUserRole authUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据用户id查询用户所拥有的角色id
     * @param userId
     * @return
     */
    List<Long> queryByUserId(Long userId);


}
