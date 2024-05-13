package com.xiaoshouwaliang.subject.infra.entity;

import lombok.Data;

/**
 * @author 小手WA凉
 * @date 2024-05-03
 */
@Data
public class UserInfo {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
}
