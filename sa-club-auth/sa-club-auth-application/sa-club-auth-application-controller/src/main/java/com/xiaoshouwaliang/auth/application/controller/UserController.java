package com.xiaoshouwaliang.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.base.Preconditions;
import com.xiaoshouwaliang.auth.application.controller.converter.AuthUserDTOConverter;
import com.xiaoshouwaliang.auth.application.controller.dto.AuthUserDTO;
import com.xiaoshouwaliang.auth.common.entity.Result;
import com.xiaoshouwaliang.auth.domain.entity.AuthUserBO;
import com.xiaoshouwaliang.auth.domain.service.UserInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 小手WA凉
 * @date 2024-04-23
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserInfoDomainService userInfoDomainService;

    /**
     * 用户登录
     * @param validCode
     * @return
     */
    @RequestMapping("/doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {
            Preconditions.checkNotNull(validCode,"验证码不能为空！");
            return Result.ok(userInfoDomainService.login(validCode));
        } catch (Exception e) {
            log.error("UserController.doLogin.error:{}",e.getMessage(),e);
            return Result.fail("登录失败");
        }
    }

    /**
     * 用户退出
     * @return
     */

    @PostMapping("/logOut")
    public Result<String> logout(@RequestParam String userName){
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(userName), "用户名不能为空");
            userInfoDomainService.logOut(userName);
            return Result.ok("退出登录成功");
        } catch (Exception e) {
            log.error("UserController.logout.error:{}",e.getMessage(),e);
            return Result.fail("退出登录失败");
        }
    }
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
            log.error("UserController.userRegister.error:{}",e.getMessage(),e);
            return Result.fail("注册用户失败");
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
            log.error("UserController.userUpdate.error:{}",e.getMessage(),e);
            return Result.fail("更新用户信息失败");
        }
    }

    /**
     * 删除用户
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
            log.error("UserController.userDelete.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 用户启动禁用
     * @param authUserDTO
     * @return
     */
    @PostMapping("/changeStatus")
    public Result<Boolean> userChangeStatus(@RequestBody AuthUserDTO authUserDTO){
        try {
            Preconditions.checkNotNull(authUserDTO.getId(),"id不能为空");
            Preconditions.checkNotNull(authUserDTO.getStatus(),"用户状态不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.authUserDTOtoBO(authUserDTO);
            userInfoDomainService.changeUserStatus(authUserBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("UserController.userChangeStatus.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 查询用户基本信息
     * @param authUserDTO
     * @return
     */
    @PostMapping("/getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO){
        try {
            Preconditions.checkNotNull(authUserDTO.getUserName(),"用户名不能为空");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.authUserDTOtoBO(authUserDTO);
            AuthUserBO resultBO = userInfoDomainService.queryUserInfo(authUserBO);
            AuthUserDTO resultDTO = AuthUserDTOConverter.INSTANCE.authUserBOtoDTO(resultBO);
            return Result.ok(resultDTO);
        } catch (Exception e) {
            log.error("UserController.getUserInfo.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }
}

