package com.clients.app.authentication.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class UserTokenAuthenticationDtoResponse {

    private String username;

    private List<RoleDtoResponse> roles;
}
