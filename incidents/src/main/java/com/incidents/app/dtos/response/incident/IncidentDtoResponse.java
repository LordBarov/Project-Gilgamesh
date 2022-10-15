package com.incidents.app.dtos.response.incident;

import com.clients.app.authentication.dtos.response.UserDtoResponse;
import com.incidents.app.dtos.response.TaskDtoResponse;
import com.incidents.app.dtos.response.dictionaries.CategoryDtoResponse;
import com.incidents.app.dtos.response.dictionaries.PriorityLevelDtoResponse;
import com.incidents.app.dtos.response.dictionaries.TagDtoResponse;
import com.incidents.app.dtos.response.dictionaries.TypeDtoResponse;
import lombok.Data;

import java.util.List;

@Data
public class IncidentDtoResponse {

    private String title;

    private String description;

    private PriorityLevelDtoResponse priorityLevel;

    private CategoryDtoResponse category;

    private List<TypeDtoResponse> types;

    private List<TagDtoResponse> tags;

    private List<TaskDtoResponse> tasks;

    private List<UserDtoResponse> users;
}
