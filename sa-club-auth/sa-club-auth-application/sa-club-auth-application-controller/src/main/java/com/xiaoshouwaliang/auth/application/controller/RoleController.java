package com.xiaoshouwaliang.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaoshouwaliang.auth.application.controller.converter.AuthRoleDTOConverter;
import com.xiaoshouwaliang.auth.application.controller.dto.AuthRoleDTO;
import com.xiaoshouwaliang.auth.domain.entity.AuthRoleBO;
import com.xiaoshouwaliang.auth.domain.service.RoleInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import com.xiaoshouwaliang.auth.api.entity.Result;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {
    @Resource
    private RoleInfoDomainService roleInfoDomainService;

    /**
     * 新增角色
     * @param authRoleDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> addRole(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("RoleController.addRole.DTO:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()),"角色名称不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.authRoleDTOtoBO(authRoleDTO);
            roleInfoDomainService.addRole(authRoleBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("RoleController.addRole.error:{}",e.getMessage(),e);
            return Result.fail("新增角色失败");
        }
    }

    /**
     * 更新角色
     * @param authRoleDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> updateRole(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("RoleController.updateRole.DTO:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull(authRoleDTO.getId(),"角色id不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.authRoleDTOtoBO(authRoleDTO);
            return Result.ok(roleInfoDomainService.updateRole(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.updateRole.error:{}",e.getMessage(),e);
            return Result.fail("更新角色失败");
        }
    }

    /**
     * 删除角色
     * @param authRoleDTO
     * @return
     */
    @PostMapping("/delete")
    public Result<Boolean> deleteRole(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("RoleController.updateRole.DTO:{}", JSON.toJSONString(authRoleDTO));
            }
            Preconditions.checkNotNull(authRoleDTO.getId(),"角色id不能为空");
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.authRoleDTOtoBO(authRoleDTO);
            return Result.ok(roleInfoDomainService.deleteRole(authRoleBO));
        } catch (Exception e) {
            log.error("RoleController.deleteRole.error:{}",e.getMessage(),e);
            return Result.fail("删除角色失败");
        }
    }
}
