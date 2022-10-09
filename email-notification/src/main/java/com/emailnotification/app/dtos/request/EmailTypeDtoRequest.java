package com.emailnotification.app.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmailTypeDtoRequest {

    @NotNull(message = "Вы Должны указать наименование типа почты")
    private String title;
}
