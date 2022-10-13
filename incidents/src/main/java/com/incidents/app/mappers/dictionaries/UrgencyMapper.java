package com.incidents.app.mappers.dictionaries;

import com.incidents.app.dtos.response.dictionaries.UrgencyDtoResponse;
import com.incidents.app.model.dictionaries.Urgency;

public class UrgencyMapper {

    public static UrgencyDtoResponse urgencyToDto(Urgency urgency) {
        UrgencyDtoResponse dtoResponse = new UrgencyDtoResponse();
        dtoResponse.setTitle(urgency.getTitle());
        dtoResponse.setColor(urgency.getColor());
        return dtoResponse;
    }
}
