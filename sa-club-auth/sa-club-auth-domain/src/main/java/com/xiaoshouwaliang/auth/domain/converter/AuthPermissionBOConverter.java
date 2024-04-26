package com.xiaoshouwaliang.auth.domain.converter;

import com.xiaoshouwaliang.auth.domain.entity.AuthPermissionBO;
import com.xiaoshouwaliang.auth.domain.entity.AuthRoleBO;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthPermission;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Mapper
public interface AuthPermissionBOConverter {
    AuthPermissionBOConverter INSTANCE = Mappers.getMapper(AuthPermissionBOConverter.class);
    AuthPermission authPermissionBOtoPOJO(AuthPermissionBO authPermissionBO);
    AuthPermissionBO authPermissionPOJOtoBO(AuthPermission authPermission);
}
