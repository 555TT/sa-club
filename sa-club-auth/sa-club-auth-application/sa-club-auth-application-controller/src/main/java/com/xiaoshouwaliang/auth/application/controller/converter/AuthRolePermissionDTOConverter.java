package com.xiaoshouwaliang.auth.application.controller.converter;

import com.xiaoshouwaliang.auth.application.controller.dto.AuthRolePermissionDTO;
import com.xiaoshouwaliang.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Mapper
public interface AuthRolePermissionDTOConverter {
    AuthRolePermissionDTOConverter INSTANCE=Mappers.getMapper(AuthRolePermissionDTOConverter.class);
    AuthRolePermissionBO authRolePermissionDTOtoBO(AuthRolePermissionDTO authRolePermissionDTO);
    AuthRolePermissionDTO authRolePermissionBOtoDTO(AuthPermissionDTOConverter authPermissionDTOConverter);

}
