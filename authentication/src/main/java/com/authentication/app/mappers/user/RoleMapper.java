package com.authentication.app.mappers.user;


import com.authentication.app.model.user.Role;
import com.clients.app.authentication.dtos.response.RoleDtoResponse;

import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleDtoResponse roleToDto(Role role) {
        RoleDtoResponse roleDtoResponse = new RoleDtoResponse();
        roleDtoResponse.setTitle(role.getTitle());
        roleDtoResponse.setPrivileges(role.getPrivileges().stream().map(PrivilegeMapper::privilegeToDto).collect(Collectors.toList()));
        return roleDtoResponse;
    }
}
