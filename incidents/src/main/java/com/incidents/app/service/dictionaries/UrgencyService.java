package com.incidents.app.service.dictionaries;

import com.incidents.app.dtos.requests.dictionaries.UrgencyDtoRequest;
import com.incidents.app.model.dictionaries.Urgency;

import java.util.List;
import java.util.Optional;

public interface UrgencyService {

    Optional<Urgency> getById(Long id);

    Urgency getByIdThrowException(Long id);

    List<Urgency> getAll();

    Urgency create(UrgencyDtoRequest dtoRequest);

    Urgency update(UrgencyDtoRequest dtoRequest, Long id);

    void delete(Long id);
}
