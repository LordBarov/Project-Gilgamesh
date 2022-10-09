package com.incidents.app.service;


import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.TaskUsers;
import com.incidents.app.repository.TaskUsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class TaskUsersServiceImplementation implements TaskUsersService{

    private final TaskUsersRepository taskUsersRepository;

    @Override
    public Optional<TaskUsers> getById(Long id) {
        return taskUsersRepository.findById(id);
    }

    @Override
    public TaskUsers getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "task_users", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<TaskUsers> getAll() {
        return null;
    }

    @Override
    public TaskUsers create() {
        return null;
    }

    @Override
    public TaskUsers update() {
        return null;
    }

    @Override
    public void delete() {

    }
}
