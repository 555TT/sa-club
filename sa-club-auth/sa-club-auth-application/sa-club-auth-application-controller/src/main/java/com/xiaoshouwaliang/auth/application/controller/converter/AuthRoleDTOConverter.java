package com.xiaoshouwaliang.auth.application.controller.converter;

import com.xiaoshouwaliang.auth.application.controller.dto.AuthRoleDTO;
import com.xiaoshouwaliang.auth.application.controller.dto.AuthUserDTO;
import com.xiaoshouwaliang.auth.domain.entity.AuthRoleBO;
import com.xiaoshouwaliang.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Mapper
public interface AuthRoleDTOConverter {
    AuthRoleDTOConverter INSTANCE=Mappers.getMapper(AuthRoleDTOConverter.class);
    AuthRoleBO authRoleDTOtoBO(AuthRoleDTO authRoleDTO);
    AuthRoleDTO authRoleBOtoDTO(AuthRoleBO authRoleBO);
}
