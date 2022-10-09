package com.incidents.app.mappers;

import com.incidents.app.dtos.response.IncidentDtoResponse;
import com.incidents.app.model.Incident;

public class IncidentMapper {

    public static IncidentDtoResponse incidentToIncidentDto(Incident incident) {
        IncidentDtoResponse incidentDtoResponse = new IncidentDtoResponse();
        incidentDtoResponse.setCategory(incident.getCategory());
        incidentDtoResponse.setDescription(incident.getDescription());
        incidentDtoResponse.setPriorityLevel(incident.getPriorityLevel());
        incidentDtoResponse.setTypes(incident.getTypes());
        incidentDtoResponse.setTitle(incident.getTitle());
        incidentDtoResponse.setUsers(incident.getUsers());
        if (incident.getTags() != null) incidentDtoResponse.setTags(incident.getTags());
        if (incident.getTasks() != null) incidentDtoResponse.setTasks(incident.getTasks());
        return incidentDtoResponse;
    }
}
