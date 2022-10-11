package com.clients.app.authentication.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class RoleDtoResponse {

    private String title;

    private List<PrivilegeDtoResponse> privileges;
}
