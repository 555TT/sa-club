package com.xiaoshouwaliang.auth.domain.converter;

import com.xiaoshouwaliang.auth.domain.entity.AuthRolePermissionBO;
import com.xiaoshouwaliang.auth.domain.entity.AuthUserBO;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthPermission;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Mapper
public interface AuthRolePermissionBOConverter {
    AuthRolePermissionBOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionBOConverter.class);
    AuthPermission authRolePermissionBOtoPOJO(AuthRolePermissionBO authRolePermissionBO);
    AuthRolePermissionBO authRolePermissionPOJOtoBO(AuthPermission authPermission);
}
