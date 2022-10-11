package com.incidents.app.dtos.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class IncidentDtoRequest {

    @NotNull(message = "Вы забыли указать наименование")
    private String title;

    private String description;

    @NotNull(message = "Вы забыли указать уровень приоритетности")
    private Long priorityLevelId;

    @NotNull(message = "Вы забыли указать категорию инцидента")
    private Long categoryId;

    @NotNull(message = "Вы забыли указать типы инцидента")
    private List<Long> typeIds;

    private List<Long> tagIds;

    @NotNull(message = "Вы забыли указать пользователей на данный проект")
    private List<Long> userIds;
}
