package com.xiaoshouwaliang.subject.application.controller;


import com.xiaoshouwaliang.subject.infra.entity.UserInfo;
import com.xiaoshouwaliang.subject.infra.rpc.UserRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小手WA凉
 * @date 2024-05-03
 */
@RequestMapping("/test")
@RestController
@Slf4j
public class FeignTestController {
    @Resource
    private UserRpc userRpc;
    @PostMapping("/feign")
    public UserInfo testFeign(String userName){
        return userRpc.getUserInfo(userName);
    }
}
