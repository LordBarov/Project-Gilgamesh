package com.incidents.app.mappers.dictionaries;

import com.incidents.app.dtos.requests.dictionaries.StatusDtoResponse;
import com.incidents.app.model.dictionaries.Status;

public class StatusMapper {

    public static StatusDtoResponse statusToDto(Status status) {
        StatusDtoResponse statusDtoResponse = new StatusDtoResponse();
        status.setColor(status.getColor());
        status.setTitle(status.getTitle());
        return statusDtoResponse;
    }
}
