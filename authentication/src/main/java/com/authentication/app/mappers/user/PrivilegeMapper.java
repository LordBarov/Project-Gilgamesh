package com.authentication.app.mappers.user;

import com.authentication.app.model.user.Privilege;
import com.clients.app.authentication.dtos.response.PrivilegeDtoResponse;

public class PrivilegeMapper {

    public static PrivilegeDtoResponse privilegeToDto(Privilege privilege) {
        PrivilegeDtoResponse privilegeDtoResponse = new PrivilegeDtoResponse();
        privilegeDtoResponse.setTitle(privilege.getTitle());
        return privilegeDtoResponse;
    }
}
