package com.incidents.app.service;

import com.incidents.app.model.TaskUsers;

import java.util.List;
import java.util.Optional;

public interface TaskUsersService {

    Optional<TaskUsers> getById(Long id);

    TaskUsers getByIdThrowException(Long id);

    List<TaskUsers> getAll();

    TaskUsers create(Long taskId, Long userId);

    void delete(Long id);
}
