package com.incidents.app.service;

import com.incidents.app.dtos.requests.TaskDtoRequest;
import com.incidents.app.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> getById(Long id);

    Task getByIdThrowException(Long id);

    List<Task> getAll();

    List<Task> getAllByIncidentId(Long id);

    Task create(TaskDtoRequest dtoRequest);

    Task update(TaskDtoRequest dtoRequest, Long id);

    void delete(Long id);
}
