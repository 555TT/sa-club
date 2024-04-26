package com.xiaoshouwaliang.auth.domain.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.auth.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.auth.domain.converter.AuthPermissionBOConverter;
import com.xiaoshouwaliang.auth.domain.entity.AuthPermissionBO;
import com.xiaoshouwaliang.auth.domain.service.PermissionInfoDomainService;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthPermission;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Service
@Slf4j
public class PermissionInfoDomainServiceImpl implements PermissionInfoDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Override
    public void addPermission(AuthPermissionBO authPermissionBO) {
        if(log.isInfoEnabled()){
            log.info("PermissionInfoDomainServiceImpl.addPermission.BO:{}", JSON.toJSONString(authPermissionBO));
        }
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.authPermissionBOtoPOJO(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        authPermission.setStatus(0);//权限状态 0启用1禁用
        authPermission.setShow(0);//展示状态 0展示 1隐藏
        authPermissionService.insert(authPermission);
    }

    @Override
    public boolean updatePermission(AuthPermissionBO authPermissionBO) {
        if(log.isInfoEnabled()){
            log.info("PermissionInfoDomainServiceImpl.addPermission.BO:{}", JSON.toJSONString(authPermissionBO));
        }
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.authPermissionBOtoPOJO(authPermissionBO);
        return authPermissionService.update(authPermission);
    }

    @Override
    public boolean deletePermission(AuthPermissionBO authPermissionBO) {
        if(log.isInfoEnabled()){
            log.info("PermissionInfoDomainServiceImpl.addPermission.BO:{}", JSON.toJSONString(authPermissionBO));
        }
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE.authPermissionBOtoPOJO(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.code);
        return authPermissionService.update(authPermission);
    }
}
