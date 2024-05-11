package com.xiaoshouwaliang.subject.infra.rpc;

import com.xiaoshouwaliang.auth.api.entity.AuthUserDTO;
import com.xiaoshouwaliang.auth.api.entity.Result;
import com.xiaoshouwaliang.auth.api.feignservice.UserFeignService;
import com.xiaoshouwaliang.subject.infra.entity.UserInfo;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author 小手WA凉
 * @date 2024-05-03
 */
@Component
public class UserRpc {
    @Resource
    private UserFeignService userFeignService;
    public UserInfo getUserInfo(String userName){
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUserName(userName);
        Result<AuthUserDTO> result = userFeignService.getUserInfo(authUserDTO);
        UserInfo userInfo = new UserInfo();
        if(!result.getSuccess()){
            return userInfo;
        }
        AuthUserDTO data = result.getData();
        userInfo.setNickName(data.getNickName());
        userInfo.setAvatar(data.getAvatar());
        userInfo.setUserName(data.getUserName());
        return userInfo;
    }
}
