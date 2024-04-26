package com.xiaoshouwaliang.auth.application.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.google.common.base.Preconditions;
import com.xiaoshouwaliang.auth.application.controller.converter.AuthUserDTOConverter;
import com.xiaoshouwaliang.auth.application.controller.dto.AuthUserDTO;
import com.xiaoshouwaliang.auth.common.entity.Result;
import com.xiaoshouwaliang.auth.domain.entity.AuthUserBO;
import com.xiaoshouwaliang.auth.domain.service.UserInfoDomainService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小手WA凉
 * @date 2024-04-23
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Resource
    private UserInfoDomainService userInfoDomainService;

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }


    /**
     * 用户注册
     * @param authUserDTO
     * @return
     */
    @PostMapping("/register")
    public Result<Boolean> userRegister(@RequestBody AuthUserDTO authUserDTO){
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()),"用户名不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.authUserDTOtoBO(authUserDTO);
            boolean result = userInfoDomainService.addUser(authUserBO);
            if(result==true)
            return Result.ok(true);
            else
                return Result.fail("该用户名已存在");
        } catch (Exception e) {
            return Result.fail("注册用户失败："+e.getMessage());
        }
    }

    /**
     * 更新用户信息
     * @param authUserDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> userUpdate(@RequestBody AuthUserDTO authUserDTO){
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()),"用户名不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.authUserDTOtoBO(authUserDTO);
            userInfoDomainService.updateUser(authUserBO);
            return Result.ok(true);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 删除用户
     *
     * @param authUserDTO
     * @return
     */
    @PostMapping("/delete")
    public Result userDelete(@RequestBody AuthUserDTO authUserDTO){
        try {
            Preconditions.checkNotNull(authUserDTO.getId(),"id不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.authUserDTOtoBO(authUserDTO);
            userInfoDomainService.deleteUser(authUserBO);
            return Result.ok(true);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
    @PostMapping("/changeStatus")
    public Result<Boolean> userChangeStatus(@RequestBody AuthUserDTO authUserDTO){
        try {
            Preconditions.checkNotNull(authUserDTO.getId(),"id不能为空");
            Preconditions.checkNotNull(authUserDTO.getStatus(),"用户状态不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.authUserDTOtoBO(authUserDTO);
            userInfoDomainService.changeUserStatus(authUserBO);
            return Result.ok(true);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
    @PostMapping("/getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO){
        try {
            Preconditions.checkNotNull(authUserDTO.getUserName(),"用户名不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.authUserDTOtoBO(authUserDTO);
            AuthUserBO resultBO = userInfoDomainService.queryUserInfo(authUserBO);
            AuthUserDTO resultDTO = AuthUserDTOConverter.INSTANCE.authUserBOtoDTO(resultBO);
            return Result.ok(resultDTO);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
}

