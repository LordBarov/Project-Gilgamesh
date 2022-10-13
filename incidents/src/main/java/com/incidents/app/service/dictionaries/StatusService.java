package com.incidents.app.service.dictionaries;

import com.incidents.app.dtos.requests.dictionaries.StatusDtoRequest;
import com.incidents.app.model.dictionaries.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {

    Optional<Status> getById(Long id);

    Status getByIdThrowException(Long id);

    List<Status> getAll();

    Status create(StatusDtoRequest statusDtoRequest);

    Status update(StatusDtoRequest statusDtoRequest, Long id);

    void delete(Long id);
}
