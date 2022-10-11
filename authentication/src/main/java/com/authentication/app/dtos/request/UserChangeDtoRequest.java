package com.authentication.app.dtos.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class UserChangeDtoRequest {

    @NotNull(message = "Поле логина не может быть пустым")
    private String username;

    @NotNull(message = "Поле пароля не может быть пустым")
    private String password;

    private MultipartFile avatar;
}
