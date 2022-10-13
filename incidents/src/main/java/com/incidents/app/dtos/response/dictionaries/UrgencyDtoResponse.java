package com.incidents.app.dtos.response.dictionaries;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UrgencyDtoResponse {

    private String title;

    private String color;
}
