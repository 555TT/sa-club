package com.xiaoshouwaliang.auth.domain.service;

import com.xiaoshouwaliang.auth.domain.entity.AuthUserBO;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
public interface UserInfoDomainService {

    /**
     * 新增用户
     * @param authUserBO
     */
    boolean addUser(AuthUserBO authUserBO);


    /**
     * 更新用户信息
     * @param authUserBO
     */
    void updateUser(AuthUserBO authUserBO);


    /**
     * 删除用户
     * @param authUserBO
     */
    void deleteUser(AuthUserBO authUserBO);


    /**
     * 改变用户启用禁用状态 0启用1禁用
     * @param authUserBO
     */
    void changeUserStatus(AuthUserBO authUserBO);

    /**
     * 查询用户基本信息
     * @param authUserBO
     * @return
     */
    AuthUserBO queryUserInfo(AuthUserBO authUserBO);

    Object login(String validCode);
}
