package com.xiaoshouwaliang.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaoshouwaliang.auth.application.controller.converter.AuthRolePermissionDTOConverter;
import com.xiaoshouwaliang.auth.application.controller.dto.AuthRolePermissionDTO;
import com.xiaoshouwaliang.auth.common.entity.Result;
import com.xiaoshouwaliang.auth.domain.entity.AuthRolePermissionBO;
import com.xiaoshouwaliang.auth.domain.service.AuthRolePermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/rolePermission")
@Slf4j
public class RolePermissionController {


    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;
    @PostMapping("/add")
    public Result<Boolean> addRolePermission(@RequestBody AuthRolePermissionDTO authRolePermissionDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("RolePermissionController.addRolePermission.DTO:{}", JSON.toJSONString(authRolePermissionDTO));
            }
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(),"角色id不能为空");
            Preconditions.checkNotNull(authRolePermissionDTO.getPermissionIdList(),"权限id集合不能为空");
            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE.authRolePermissionDTOtoBO(authRolePermissionDTO);
            authRolePermissionDomainService.addRolePermission(authRolePermissionBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("RolePermissionController.RolePermissionController.error:{}",e.getMessage(),e);
            return Result.fail("添加角色权限关联失败");
        }
    }
}
