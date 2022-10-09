package com.incidents.app.dtos.response;

import com.incidents.app.model.Task;
import com.incidents.app.model.dictionaries.Category;
import com.incidents.app.model.dictionaries.PriorityLevel;
import com.incidents.app.model.dictionaries.Tag;
import com.incidents.app.model.dictionaries.Type;
import com.incidents.app.model.user.User;
import lombok.Data;

import java.util.List;

@Data
public class IncidentDtoResponse {

    private String title;

    private String description;

    private PriorityLevel priorityLevel;

    private Category category;

    private List<Type> types;

    private List<Tag> tags;

    private List<Task> tasks;

    private List<User> users;
}
