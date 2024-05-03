package com.xiaoshouwaliang.auth.api.feignservice;

import com.xiaoshouwaliang.auth.api.entity.AuthUserDTO;
import com.xiaoshouwaliang.auth.api.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 小手WA凉
 * @date 2024-05-03
 */
@FeignClient(value = "sa-club-auth",path = "/user")
public interface UserFeignService {
    @PostMapping("/getUserInfo")
    Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO);
}
