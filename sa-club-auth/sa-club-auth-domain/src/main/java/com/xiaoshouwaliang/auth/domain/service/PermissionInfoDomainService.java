package com.xiaoshouwaliang.auth.domain.service;

import com.xiaoshouwaliang.auth.domain.entity.AuthPermissionBO;
import com.xiaoshouwaliang.auth.domain.entity.AuthRoleBO;

import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
public interface PermissionInfoDomainService {


    /**
     * 新增权限
     * @param authPermissionBO
     */
    void addPermission(AuthPermissionBO authPermissionBO);

    /**
     * 更新权限
     * @param authPermissionBO
     * @return
     */
    boolean updatePermission(AuthPermissionBO authPermissionBO);

    /**
     * 删除权限
     * @param authPermissionBO
     * @return
     */
    boolean deletePermission(AuthPermissionBO authPermissionBO);

    /**
     * 根据用户名查询用户权限
     * @param userName
     * @return
     */
    List<String> queryPermission(String userName);
}
