package com.xiaoshouwaliang.practice.server.rpc;
import com.xiaoshouwaliang.auth.api.entity.AuthUserDTO;
import com.xiaoshouwaliang.auth.api.entity.Result;
import com.xiaoshouwaliang.auth.api.feignservice.UserFeignService;
import com.xiaoshouwaliang.practice.server.entity.dto.UserInfo;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
@Component
public class UserRpc {

    @Resource
    private UserFeignService userFeignService;

    public UserInfo getUserInfo(String userName) {
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUserName(userName);
        Result<AuthUserDTO> result = userFeignService.getUserInfo(authUserDTO);
        UserInfo userInfo = new UserInfo();
        if (!result.getSuccess()) {
            return userInfo;
        }
        AuthUserDTO data = result.getData();
        userInfo.setUserName(data.getUserName());
        userInfo.setNickName(data.getNickName());
        userInfo.setAvatar(data.getAvatar());
        return userInfo;
    }

}
