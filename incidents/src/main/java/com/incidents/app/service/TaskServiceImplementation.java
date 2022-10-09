package com.incidents.app.service;

import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.Task;
import com.incidents.app.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Log4j2
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "task", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public Task create() {
        return null;
    }

    @Override
    public Task update() {
        return null;
    }

    @Override
    public void delete() {

    }
}
