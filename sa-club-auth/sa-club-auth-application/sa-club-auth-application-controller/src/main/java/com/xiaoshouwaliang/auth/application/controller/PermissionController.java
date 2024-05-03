package com.xiaoshouwaliang.auth.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaoshouwaliang.auth.api.entity.Result;
import com.xiaoshouwaliang.auth.application.controller.converter.AuthPermissionDTOConverter;
import com.xiaoshouwaliang.auth.application.controller.dto.AuthPermissionDTO;
import com.xiaoshouwaliang.auth.domain.entity.AuthPermissionBO;
import com.xiaoshouwaliang.auth.domain.service.PermissionInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/permission")
@Slf4j
public class PermissionController {
    @Resource
    private PermissionInfoDomainService permissionInfoDomainService;


    /**
     * 新增权限
     * @param authPermissionDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> addPermission(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("PermissionController.addPermission.DTO:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()),"权限名称不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(),"权限父id不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getPermissionKey()),"权限key不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.authPermissionDTOtoBO(authPermissionDTO);
            permissionInfoDomainService.addPermission(authPermissionBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("PermissionController.addPermission.error:{}",e.getMessage(),e);
            return Result.fail("新增权限失败");
        }
    }
    @PostMapping("/update")
    public Result<Boolean> updatePermission(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("PermissionController.updatePermission.DTO:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(),"权限id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.authPermissionDTOtoBO(authPermissionDTO);
            boolean result =permissionInfoDomainService.updatePermission(authPermissionBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("RoleController.updatePermission.error:{}",e.getMessage(),e);
            return Result.fail("更新权限失败");
        }
    }
    @PostMapping("/delete")
    public Result<Boolean> deletePermission(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("PermissionController.deletePermission.DTO:{}", JSON.toJSONString(authPermissionDTO));
            }
            Preconditions.checkNotNull(authPermissionDTO.getId(),"权限id不能为空");
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.authPermissionDTOtoBO(authPermissionDTO);
            boolean result= permissionInfoDomainService.deletePermission(authPermissionBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("RoleController.deletePermission.error:{}",e.getMessage(),e);
            return Result.fail("删除权限失败");
        }
    }
    @GetMapping("/getPermission")
    public Result<List<String>> getPermission(String userName){
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(userName),"用户名不能为空");
             List<String> permissions=permissionInfoDomainService.queryPermission(userName);
             return Result.ok(permissions);
        } catch (Exception e) {
            log.error("RoleController.getPermission.error:{}",e.getMessage(),e);
            return Result.fail("获取权限失败");
        }
    }

}
