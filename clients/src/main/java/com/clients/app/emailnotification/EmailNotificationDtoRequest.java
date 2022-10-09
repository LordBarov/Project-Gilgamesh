package com.clients.app.emailnotification;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmailNotificationDtoRequest {

    @NotNull(message = "наименование не должно быть пустым")
    private String title;

    @NotNull(message = "описание не должно быть пустым")
    private String description;

    @NotNull(message = "тип почты не должен быть пустым")
    private Long emailType;
}
