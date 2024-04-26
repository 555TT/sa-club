package com.xiaoshouwaliang.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (AuthRole)DTO
 *
 * @author makejava
 * @since 2024-04-25 15:40:48
 */
@Data
public class AuthRoleBO implements Serializable {

    private Long id;
/**
     * 角色名称
     */
    private String roleName;
/**
     * 角色唯一标识
     */
    private String roleKey;
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


}

