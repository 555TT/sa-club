package com.xiaoshouwaliang.auth.domain.converter;

import com.xiaoshouwaliang.auth.domain.entity.AuthRoleBO;
import com.xiaoshouwaliang.auth.domain.entity.AuthUserBO;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthRole;
import com.xiaoshouwaliang.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Mapper
public interface AuthRoleBOConverter {
    AuthRoleBOConverter INSTANCE = Mappers.getMapper(AuthRoleBOConverter.class);
    AuthRole authRoleBOtoPOJO(AuthRoleBO authRoleBO);
    AuthRoleBO authRolePOJOtoBO(AuthRole authRole);
}
