package com.xiaoshouwaliang.auth.domain.service.serviceImpl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.auth.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.auth.domain.constants.AuthConstant;
import com.xiaoshouwaliang.auth.domain.converter.AuthUserBOConverter;
import com.xiaoshouwaliang.auth.domain.entity.AuthUserBO;
import com.xiaoshouwaliang.auth.domain.redis.RedisUtil;
import com.xiaoshouwaliang.auth.domain.service.UserInfoDomainService;
import com.xiaoshouwaliang.auth.infra.basic.entity.*;
import com.xiaoshouwaliang.auth.infra.basic.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Service
@Slf4j
public class UserInfoDomainServiceImpl implements UserInfoDomainService {
    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthRoleService authRoleService;
    @Resource
    private AuthUserRoleService authUserRoleService;
    @Resource
    private AuthRolePermissionService authRolePermissionService;
    @Resource
    private AuthPermissionService authPermissionService;
    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";
    private String authRolePrefix = "auth.role";

    @Value("${AES.key}")
    private String key;//对称加密key

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUser(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.authUserBOtoPOJO(authUserBO);
        //判断用户名是否已经存在
        AuthUser existUser = authUserService.queryByUserName(authUser);
        if (existUser != null) {
            return false;
        }
        //插入用户基本信息表
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        authUser.setStatus(0);//用户启用禁用状态 0启用1禁用
        if (authUser.getPassword() != null) {
            authUser.setPassword(SaSecureUtil.aesEncrypt(key, authUser.getPassword()));//采用AEC对称加密算法密码加密
        }
/*        if(log.isInfoEnabled()){
            log.info("解密后密码：{}",SaSecureUtil.aesDecrypt(key,authUser.getPassword()));
        }*/
        authUserService.insert(authUser);
        //建立初步的用户角色关联（插入用户角色表）
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole resultRole = authRoleService.queryByCondition(authRole);
        Long roleId = resultRole.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setRoleId(roleId);
        authUserRole.setUserId(userId);
        authUserRoleService.insert(authUserRole);
        //将用户的角色信息和权限信息写入redis
        String redisRoleKey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        ArrayList<String> strings = new ArrayList<>();
        strings.add(AuthConstant.NORMAL_USER);
        redisUtil.set(redisRoleKey, JSON.toJSONString(strings,true));
        String redisPermissionKey = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
             //根据role_id查permission_id
        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(roleId);
        List<AuthRolePermission> rolePermissions = authRolePermissionService.queryByCondition(authRolePermission);
        if(rolePermissions!=null&&rolePermissions.size()>0){
            List<Long> permissionIds = rolePermissions.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
            List<AuthPermission> permissions = authPermissionService.queryByIds(permissionIds);
            List<String> permissionKeys = permissions.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
            redisUtil.set(redisPermissionKey, JSON.toJSONString(permissionKeys,true));
        }
        return true;
    }

    @Override
    public void updateUser(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.authUserBOtoPOJO(authUserBO);
        authUserService.update(authUser);
    }

    @Override
    public void deleteUser(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.authUserBOtoPOJO(authUserBO);
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.code);
        authUserService.update(authUser);
    }

    @Override
    public void changeUserStatus(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.authUserBOtoPOJO(authUserBO);
        authUserService.update(authUser);
    }

    @Override
    public AuthUserBO queryUserInfo(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.authUserBOtoPOJO(authUserBO);
        AuthUser result = authUserService.queryByUserName(authUser);
        return AuthUserBOConverter.INSTANCE.authUserPOJOtoBO(result);
    }

    @Override
    public SaTokenInfo login(String validCode) {
        String openId = redisUtil.get("loginKey." + validCode);
        if (openId == null || StringUtils.isBlank(openId)) {
            return null;
        }
        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        addUser(authUserBO);
        redisUtil.del("loginKey." + validCode);
        /*//将用户的角色信息和权限信息写入redis
        AuthUser authUser = new AuthUser();
        authUser.setUserName(openId);
        AuthUser authUserResult = authUserService.queryByUserName(authUser);
        List<Long> roleIds = authUserRoleService.queryByUserId(authUserResult.getId());
        List<AuthRole> authRoles = authRoleService.queryByIds(roleIds);
        List<String> roleKeys = authRoles.stream().map(AuthRole::getRoleKey).collect(Collectors.toList());
        String redisRoleKey = redisUtil.buildKey(authRolePrefix, openId);
        redisUtil.set(redisRoleKey, JSON.toJSONString(roleKeys));
        List<Long> permissionIds = authRolePermissionService.queryByRoleIds(roleIds);
        List<AuthPermission> permissions = authPermissionService.queryByIds(permissionIds);
        List<String> permissionKeys = permissions.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        String redisPermissionKey = redisUtil.buildKey(authPermissionPrefix, openId);
        redisUtil.set(redisPermissionKey, JSON.toJSONString(permissionKeys));*/
        //登录
        StpUtil.login(openId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return tokenInfo;

    }

    @Override
    public void logOut(String userName) {
        String redisRoleKey = redisUtil.buildKey(authRolePrefix, userName);
        String redisPermissionKey = redisUtil.buildKey(authPermissionPrefix, userName);
        redisUtil.del(redisPermissionKey);
        redisUtil.del(redisRoleKey);
        StpUtil.logout(userName);
    }
}
