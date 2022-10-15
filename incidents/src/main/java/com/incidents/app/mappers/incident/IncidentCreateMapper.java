package com.incidents.app.mappers.incident;

import com.clients.app.authentication.dtos.response.UserDtoResponse;
import com.incidents.app.dtos.response.incident.IncidentCreateDtoResponse;
import com.incidents.app.mappers.dictionaries.CategoryMapper;
import com.incidents.app.mappers.dictionaries.PriorityLevelMapper;
import com.incidents.app.mappers.dictionaries.TagMapper;
import com.incidents.app.mappers.dictionaries.TypeMapper;
import com.incidents.app.model.Incident;

import java.util.List;

public class IncidentCreateMapper {

    public static IncidentCreateDtoResponse incidentCreateToDto(Incident incident, List<UserDtoResponse> users) {
        IncidentCreateDtoResponse incidentDtoResponse = new IncidentCreateDtoResponse();
        incidentDtoResponse.setTitle(incident.getTitle());
        incidentDtoResponse.setDescription(incident.getDescription());
        incidentDtoResponse.setCategory(CategoryMapper.categoryToDto(incident.getCategory()));
        incidentDtoResponse.setPriorityLevel(PriorityLevelMapper.priorityLevelToDto(incident.getPriorityLevel()));
        incidentDtoResponse.setTypes(incident.getTypes().stream().map(TypeMapper::typeToDto).toList());
        if (incident.getTags() != null) incidentDtoResponse.setTags(incident.getTags().stream().map(TagMapper::tagToDto).toList());
        if (users != null) incidentDtoResponse.setUsers(users);
        return incidentDtoResponse;
    }
}
