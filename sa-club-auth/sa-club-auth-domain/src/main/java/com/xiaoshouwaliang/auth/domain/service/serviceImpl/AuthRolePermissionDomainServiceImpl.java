package com.xiaoshouwaliang.auth.domain.service.serviceImpl;

import com.xiaoshouwaliang.auth.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.auth.domain.entity.AuthRolePermissionBO;
import com.xiaoshouwaliang.auth.domain.service.AuthRolePermissionDomainService;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthRolePermission;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Service
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {


    @Resource
    private AuthRolePermissionService authRolePermissionService;
    @Override
    public void addRolePermission(AuthRolePermissionBO authRolePermissionBO) {
        List<AuthRolePermission> authRolePermissions=new ArrayList<>();
        Long roleId = authRolePermissionBO.getRoleId();
        List<Integer> permissionIdList = authRolePermissionBO.getPermissionIdList();
        for (Integer permissionId:permissionIdList){
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId.longValue());
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
            authRolePermissions.add(authRolePermission);
        }
        authRolePermissionService.batchInsert(authRolePermissions);
    }
}
