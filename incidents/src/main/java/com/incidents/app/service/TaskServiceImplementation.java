package com.incidents.app.service;

import com.incidents.app.dtos.requests.TaskDtoRequest;
import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomCouldNotCreateException;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.exception.domain.CustomUsersNotInListException;
import com.incidents.app.model.IncidentUsers;
import com.incidents.app.model.Task;
import com.incidents.app.repository.TaskRepository;
import com.incidents.app.service.dictionaries.StatusService;
import com.incidents.app.service.dictionaries.UrgencyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Log4j2
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;
    private final StatusService statusService;
    private final IncidentService incidentService;
    private final UrgencyService urgencyService;
    private final TaskUsersService taskUsersService;
    private final IncidentUsersService incidentUsersService;


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
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getAllByIncidentId(Long id) {
        return taskRepository.getAllByIncidentIdOrderByCreatedDateDesc(id);
    }

    @Override
    public Task create(TaskDtoRequest dtoRequest) {
        Task task;
        List<IncidentUsers> incidentUsers;
        try {
            task = new Task();
            incidentUsers = incidentUsersService.getAllByIncidentId(dtoRequest.getIncidentId());
            task.setTitle(dtoRequest.getTitle());
            task.setDescription(dtoRequest.getDescription());
            task.setStatus(statusService.getByIdThrowException(dtoRequest.getStatusId()));
            task.setIncident(incidentService.getByIdThrowException(dtoRequest.getIncidentId()));
            task.setUrgency(urgencyService.getByIdThrowException(dtoRequest.getUrgencyId()));

            if (areUsersValid(incidentUsers, dtoRequest.getUserIds())) dtoRequest.getUserIds().forEach(userId -> taskUsersService.create(task.getId(), userId));
            else throw new CustomUsersNotInListException(CustomStatusCode.USERS_ARE_NOT_IN_LIST.getCode());

            return this.save(task);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public Task update(TaskDtoRequest dtoRequest, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private Task save(Task task) {
        return taskRepository.save(task);
    }

    private boolean areUsersValid(List<IncidentUsers> incidentUsers, List<Long> userIds) {
        int length = incidentUsers.size();
        int counter = 0;
        for (IncidentUsers incidentUser : incidentUsers) {
            for (Long userId : userIds) {
                if (userId.equals(incidentUser.getUserId())) {
                    counter++;
                }
            }
        }
        return counter == length;
    }
}
