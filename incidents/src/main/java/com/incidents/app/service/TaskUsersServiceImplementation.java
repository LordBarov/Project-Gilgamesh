package com.incidents.app.service;


import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomCouldNotCreateException;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.TaskUsers;
import com.incidents.app.repository.TaskUsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class TaskUsersServiceImplementation implements TaskUsersService{

    private final TaskUsersRepository taskUsersRepository;
    private final TaskService taskService;

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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TaskUsers create(Long taskId, Long userId) {
        TaskUsers taskUsers;
        try {
            taskUsers = new TaskUsers();
            taskUsers.setTask(taskService.getByIdThrowException(taskId));
            taskUsers.setUserId(userId);

            return this.save(taskUsers);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }


    @Override
    public void delete(Long id) {

    }

    private TaskUsers save(TaskUsers taskUsers) {
        return taskUsersRepository.save(taskUsers);
    }
}
