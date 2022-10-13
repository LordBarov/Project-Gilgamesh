package com.incidents.app.dtos.requests.dictionaries;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatusDtoRequest {

    @NotNull(message = "Наименование не должно быть пустым")
    private String title;

    @NotNull(message = "Цвет не должно быть пустым")
    private String color;


}
