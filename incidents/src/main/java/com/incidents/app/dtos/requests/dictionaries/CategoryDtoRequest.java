package com.incidents.app.dtos.requests.dictionaries;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDtoRequest {

    @NotNull(message = "Поле Наименования не должно быть пустым")
    private String title;
}
