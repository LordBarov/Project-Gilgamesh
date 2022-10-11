package com.clients.app.authentication.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TokenAuthenticationDtoRequest {

    @NotNull(message = "ip не должен быть пустым")
    private String userIp;

    @NotNull(message = "токен не должен быть пустым")
    private String token;
}
