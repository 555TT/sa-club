package com.xiaoshouwaliang.auth.domain.service;

import com.xiaoshouwaliang.auth.domain.entity.AuthRolePermissionBO;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthRolePermission;


public interface AuthRolePermissionDomainService {


    /**
     * 添加角色与权限的映射关系
     * @param authRolePermissionBO
     * @return
     */
    void addRolePermission(AuthRolePermissionBO authRolePermissionBO);
}
