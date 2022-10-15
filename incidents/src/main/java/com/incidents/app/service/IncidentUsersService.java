package com.incidents.app.service;

import com.incidents.app.model.Incident;
import com.incidents.app.model.IncidentUsers;

import java.util.List;
import java.util.Optional;

public interface IncidentUsersService {

    Optional<IncidentUsers> getById(Long id);

    IncidentUsers getByIdThrowException(Long id);

    List<IncidentUsers> getAll();

    List<IncidentUsers> getAllByIncidentId(Long id);

    IncidentUsers create(Long incidentId, Long userId);

    void delete(Long id);
}
