package com.xiaoshouwaliang.practice.server.utils;

import com.xiaoshouwaliang.practice.server.config.context.LoginContextHolder;

/**登录工具类
 * @author 小手WA凉
 * @date 2024-05-03
 */
public class LoginUtil {
    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }
}
