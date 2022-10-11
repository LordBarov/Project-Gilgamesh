package com.authentication.app.dtos.request;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDtoRequest {

    @NotNull(message = "Юзернейм не должен быть пустым")
    private String username;

    @NotNull(message = "Паоль не должен быть пустым")
    private String password;
}
