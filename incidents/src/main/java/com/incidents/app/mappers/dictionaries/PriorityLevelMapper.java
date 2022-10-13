package com.incidents.app.mappers.dictionaries;

import com.incidents.app.dtos.response.dictionaries.PriorityLevelDtoResponse;
import com.incidents.app.model.dictionaries.PriorityLevel;

public class PriorityLevelMapper {

    public static PriorityLevelDtoResponse priorityLevelToDto(PriorityLevel priorityLevel) {
        PriorityLevelDtoResponse priorityLevelDtoResponse = new PriorityLevelDtoResponse();
        priorityLevel.setTitle(priorityLevel.getTitle());
        priorityLevel.setColor(priorityLevel.getColor());
        return priorityLevelDtoResponse;
    }
}
