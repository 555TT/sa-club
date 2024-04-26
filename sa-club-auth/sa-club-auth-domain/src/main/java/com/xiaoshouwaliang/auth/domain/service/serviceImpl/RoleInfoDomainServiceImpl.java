package com.xiaoshouwaliang.auth.domain.service.serviceImpl;
import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.auth.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.auth.domain.converter.AuthRoleBOConverter;
import com.xiaoshouwaliang.auth.domain.entity.AuthRoleBO;
import com.xiaoshouwaliang.auth.domain.service.RoleInfoDomainService;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthRole;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Service
@Slf4j
public class RoleInfoDomainServiceImpl implements RoleInfoDomainService {
    @Resource
    private AuthRoleService authRoleService;

    @Override
    public void addRole(AuthRoleBO authRoleBO) {
        if(log.isInfoEnabled()){
            log.info("RoleInfoDomainServiceImpl.addRole.Bo:{}", JSON.toJSONString(authRoleBO));
        }
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.authRoleBOtoPOJO(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        authRoleService.insert(authRole);
    }

    @Override
    public boolean updateRole(AuthRoleBO authRoleBO) {
        if(log.isInfoEnabled()){
            log.info("RoleInfoDomainServiceImpl.addRole.Bo:{}", JSON.toJSONString(authRoleBO));
        }
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.authRoleBOtoPOJO(authRoleBO);
        return authRoleService.update(authRole)>0;
    }

    @Override
    public boolean deleteRole(AuthRoleBO authRoleBO) {
        if(log.isInfoEnabled()){
            log.info("RoleInfoDomainServiceImpl.addRole.Bo:{}", JSON.toJSONString(authRoleBO));
        }
        AuthRole authRole = AuthRoleBOConverter.INSTANCE.authRoleBOtoPOJO(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.code);
        int update = authRoleService.update(authRole);
        return update>0;
    }
}
