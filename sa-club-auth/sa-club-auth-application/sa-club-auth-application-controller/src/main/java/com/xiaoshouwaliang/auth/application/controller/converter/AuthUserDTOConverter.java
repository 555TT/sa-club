package com.xiaoshouwaliang.auth.application.controller.converter;

import com.xiaoshouwaliang.auth.api.entity.AuthUserDTO;
import com.xiaoshouwaliang.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 小手WA凉
 * @date 2024-04-25
 */
@Mapper
public interface AuthUserDTOConverter {
    AuthUserDTOConverter INSTANCE=Mappers.getMapper(AuthUserDTOConverter.class);
    AuthUserBO authUserDTOtoBO(AuthUserDTO authUserDTO);
    AuthUserDTO authUserBOtoDTO(AuthUserBO authUserBO);
}
