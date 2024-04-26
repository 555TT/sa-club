package com.xiaoshouwaliang.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.gateway.redis.RedisUtil;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-24
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix="auth.permission";
    private String authRolePrefix="auth.role";

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        return getAuth(loginId.toString(),authPermissionPrefix);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        return getAuth(loginId.toString(),authRolePrefix);
    }

    private List<String> getAuth(Object loginId,String prefix){
        String key = redisUtil.buildKey(prefix, loginId.toString());
        String value = redisUtil.get(key);
        if(StringUtils.isBlank(value)){
            return Collections.emptyList();
        }
       return JSON.parseObject(value,List.class);
    }
}
