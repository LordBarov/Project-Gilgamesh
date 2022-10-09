package com.incidents.app.service;


import com.clients.app.emailnotification.EmailNotificationDtoRequest;
import com.incidents.app.dtos.requests.IncidentDtoRequest;
import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomCouldNotCreateException;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.Incident;
import com.incidents.app.model.dictionaries.PriorityLevel;
import com.incidents.app.repository.IncidentRepository;
import com.incidents.app.service.dictionaries.*;
import com.incidents.app.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class IncidentServiceImplementation implements IncidentService{

    private final IncidentRepository incidentRepository;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final TypeService typeService;
    private final UserService userService;
    private final KafkaTemplate<String, EmailNotificationDtoRequest> kafkaTemplate;
    private final PriorityLevelService priorityLevelService;


    @Override
    public Optional<Incident> getById(Long id) {
        return incidentRepository.findById(id);
    }

    @Override
    public Incident getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "incident", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<Incident> getAll() {
        return incidentRepository.findAllByOrderByCreatedDateAsc();
    }

    @Override
    public Incident create(IncidentDtoRequest dtoRequest) {
        Incident incident;
        try {
            incident = new Incident();
            incident.setCategory(categoryService.getByIdThrowException(dtoRequest.getCategoryId()));
            incident.setDescription(dtoRequest.getDescription());
            incident.setPriorityLevel(priorityLevelService.getByIdThrowException(dtoRequest.getPriorityLevelId()));
            incident.setTitle(dtoRequest.getTitle());
            incident.setTags(tagService.getAllByListOfIds(dtoRequest.getTagIds()));
            incident.setTypes(typeService.getAllByListOfIds(dtoRequest.getTypeIds()));
            incident.setUsers(userService.getAllByListOfIds(dtoRequest.getUserIds()));

            EmailNotificationDtoRequest emailNotificationDtoRequest = new EmailNotificationDtoRequest();
            emailNotificationDtoRequest.setEmailType(1L);
            emailNotificationDtoRequest.setDescription("Вы создали новый инцидент под названием: " + dtoRequest.getTitle());
            emailNotificationDtoRequest.setTitle("Создание нового инцидента");
            kafkaTemplate.send("gilgamesh-notification",emailNotificationDtoRequest);

            return this.save(incident);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public Incident update(IncidentDtoRequest dtoRequest) {
        return null;
    }

    @Override
    public void delete() {

    }

    private Incident save(Incident incident) {
        return incidentRepository.save(incident);
    }
}
