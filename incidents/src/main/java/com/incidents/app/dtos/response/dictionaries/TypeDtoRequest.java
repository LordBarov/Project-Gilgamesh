package com.incidents.app.dtos.response.dictionaries;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TypeDtoRequest {

    @NotNull(message = "Наименование не должно быть пустым")
    private String title;
}
