package com.authentication.app.dtos.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class UserDtoRequest {

    @NotNull(message = "Поле Юзернейма не может быть пустым")
    private String username;

    @NotNull(message = "Поле пароля не может быть пустым")
    private String password;

    @NotNull(message = "Поле Почты не может быть пустым")
    private String email;

    private MultipartFile avatar;
}
