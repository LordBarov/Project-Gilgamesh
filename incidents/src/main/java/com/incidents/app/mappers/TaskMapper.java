package com.incidents.app.mappers;

import com.clients.app.authentication.dtos.response.UserDtoResponse;
import com.incidents.app.dtos.response.TaskDtoResponse;
import com.incidents.app.mappers.dictionaries.StatusMapper;
import com.incidents.app.mappers.dictionaries.UrgencyMapper;
import com.incidents.app.model.Task;

import java.util.List;

public class TaskMapper {

    public static TaskDtoResponse taskToDto(Task task, List<UserDtoResponse> users) {
        TaskDtoResponse taskDtoResponse = new TaskDtoResponse();
        taskDtoResponse.setTitle(task.getTitle());
        taskDtoResponse.setDescription(task.getDescription());
        taskDtoResponse.setIncidentId(task.getIncident().getId());
        taskDtoResponse.setStatusDtoResponse(StatusMapper.statusToDto(task.getStatus()));
        taskDtoResponse.setUrgencyDtoResponse(UrgencyMapper.urgencyToDto(task.getUrgency()));
        taskDtoResponse.setUsers(users);

        return taskDtoResponse;
    }
}
