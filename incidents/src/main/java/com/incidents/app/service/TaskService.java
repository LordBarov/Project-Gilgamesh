package com.incidents.app.service;

import com.incidents.app.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> getById(Long id);

    Task getByIdThrowException(Long id);

    List<Task> getAll();

    Task create();

    Task update();

    void delete();
}
