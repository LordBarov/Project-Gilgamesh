package com.incidents.app.service;

import com.incidents.app.dtos.requests.IncidentDtoRequest;
import com.incidents.app.model.Incident;

import java.util.List;
import java.util.Optional;

public interface IncidentService {

    Optional<Incident> getById(Long id);

    Incident getByIdThrowException(Long id);

    List<Incident> getAll();

    Incident create(IncidentDtoRequest dtoRequest);

    Incident update(IncidentDtoRequest dtoRequest);

    void delete();
}
