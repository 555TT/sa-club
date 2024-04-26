package com.xiaoshouwaliang.auth.application.controller.converter;

import com.xiaoshouwaliang.auth.application.controller.dto.AuthPermissionDTO;
import com.xiaoshouwaliang.auth.application.controller.dto.AuthRoleDTO;
import com.xiaoshouwaliang.auth.domain.entity.AuthPermissionBO;
import com.xiaoshouwaliang.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Mapper
public interface AuthPermissionDTOConverter {
    AuthPermissionDTOConverter INSTANCE=Mappers.getMapper(AuthPermissionDTOConverter.class);
    AuthPermissionBO authPermissionDTOtoBO(AuthPermissionDTO authPermissionDTO);
    AuthPermissionDTO authPermissionBOtoDTO(AuthPermissionBO authPermissionBO);

}
