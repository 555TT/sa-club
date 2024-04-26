package com.xiaoshouwaliang.auth.application.controller.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)DTO
 *
 * @author makejava
 * @since 2024-04-25 15:43:00
 */
@Data
public class AuthRolePermissionDTO implements Serializable {

    private Long id;
/**
     * 角色id
     */
    private Long roleId;
/**
     * 权限id
     */
    private Long permissionId;
/**
     * 创建人
     */
    private String createdBy;
/**
     * 创建时间
     */
    private Date createdTime;
/**
     * 更新人
     */
    private String updateBy;
/**
     * 更新时间
     */
    private Date updateTime;


    List<Integer> permissionIdList;
}

