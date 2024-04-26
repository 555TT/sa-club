package com.xiaoshouwaliang.auth.domain.service;

import com.xiaoshouwaliang.auth.domain.entity.AuthRoleBO;
import com.xiaoshouwaliang.auth.domain.entity.AuthUserBO;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
public interface RoleInfoDomainService {
    /**
     * 新增角色
     * @param authRoleBO
     * @return
     */
    void addRole(AuthRoleBO authRoleBO);

    /**
     * 更新角色
     * @param authRoleBO
     */
    boolean updateRole(AuthRoleBO authRoleBO);

    /**
     * 删除角色
     * @param authRoleBO
     */
    boolean deleteRole(AuthRoleBO authRoleBO);
}
