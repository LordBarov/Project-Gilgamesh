package com.clients.app.authentication.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class UserDtoResponse {

    private String username;

    private String email;

    private List<RoleDtoResponse> roles;
}
