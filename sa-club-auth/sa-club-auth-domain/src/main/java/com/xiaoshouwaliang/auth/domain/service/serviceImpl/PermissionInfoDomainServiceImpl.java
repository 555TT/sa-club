package com.xiaoshouwaliang.auth.domain.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.xiaoshouwaliang.auth.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.auth.domain.converter.AuthPermissionBOConverter;
import com.xiaoshouwaliang.auth.domain.entity.AuthPermissionBO;
import com.xiaoshouwaliang.auth.domain.redis.RedisUtil;
import com.xiaoshouwaliang.auth.domain.service.PermissionInfoDomainService;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthPermission;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthUser;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthPermissionService;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthRolePermissionService;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthUserRoleService;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Service
@Slf4j
public class PermissionInfoDomainServiceImpl implements PermissionInfoDomainService {

    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private AuthUserRoleService authUserRoleService;
    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthRolePermissionService authRolePermissionService;
    @Resource
    private RedisUtil redisUtil;
    private String authPermissionPrefix = "auth.permission";

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

    @Override
    public List<String> queryPermission(String userName) {
       /* //根据用户查询用户id
        AuthUser authUser = new AuthUser();
        authUser.setUserName(userName);
        AuthUser authUserResult = authUserService.queryByUserName(authUser);
        //根据用户id查询用户拥有的角色id
         List<Long> roleIds =authUserRoleService.queryByUserId(authUserResult.getId());
         //根据角色查询查询用户拥有的权限ids
        List<Long> permissionIds =authRolePermissionService.queryByRoleIds(roleIds);
        List<AuthPermission> permissions = authPermissionService.queryByIds(permissionIds);
        ArrayList<String> result = new ArrayList<>();
        permissions.forEach(permission->{
            result.add(permission.getPermissionKey());
        });
        return result;*/
        String buildKey = redisUtil.buildKey(authPermissionPrefix, userName);
        String permissionValue = redisUtil.get(buildKey);
        if(permissionValue==null|| StringUtils.isBlank(permissionValue)){
            return Collections.emptyList();
        }
        List<String> permissions = JSON.parseObject(permissionValue, List.class);
        return permissions;
    }
}
