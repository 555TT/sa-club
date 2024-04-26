package com.xiaoshouwaliang.auth.domain.service.serviceImpl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.xiaoshouwaliang.auth.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.auth.domain.constants.AuthConstant;
import com.xiaoshouwaliang.auth.domain.converter.AuthUserBOConverter;
import com.xiaoshouwaliang.auth.domain.entity.AuthUserBO;
import com.xiaoshouwaliang.auth.domain.service.UserInfoDomainService;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthRole;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthUser;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthUserRole;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthRoleService;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthUserRoleService;
import com.xiaoshouwaliang.auth.infra.basic.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    @Value("${AES.key}")
    private String key;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUser(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.authUserBOtoPOJO(authUserBO);
        //判断用户名是否已经存在
        AuthUser existUser = authUserService.queryByUserName(authUser);
        if(existUser!=null){
            return false;
        }
        //插入用户基本信息表
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.code);
        authUser.setStatus(0);//用户启用禁用状态 0启用1禁用
        authUser.setPassword(SaSecureUtil.aesEncrypt(key,authUser.getPassword()));//采用AEC对称加密算法密码加密
/*        if(log.isInfoEnabled()){
            log.info("解密后密码：{}",SaSecureUtil.aesDecrypt(key,authUser.getPassword()));
        }*/
        authUserService.insert(authUser);
        //建立初步的用户角色关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole resultRole = authRoleService.queryByCondition(authRole);
        Long roleId = resultRole.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setRoleId(roleId);
        authUserRole.setUserId(userId);
        authUserRoleService.insert(authUserRole);
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
        AuthUser result  = authUserService.queryByUserName(authUser);
        return AuthUserBOConverter.INSTANCE.authUserPOJOtoBO(result);
    }
}
