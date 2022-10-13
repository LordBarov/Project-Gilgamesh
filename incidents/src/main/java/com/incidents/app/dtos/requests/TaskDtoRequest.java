package com.incidents.app.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskDtoRequest {

    @NotNull(message = "наименование не должно быть пустым")
    private String title;

    @NotNull(message = "сообщение не должно быть пустым")
    private String description;

    @NotNull(message = "статус не должен быть пустым")
    private Long statusId;

    @NotNull(message = "важность не должна быть пустой")
    private Long urgencyId;

    @NotNull(message = "Вы забыли указать инцидент")
    private Long incidentId;
}
