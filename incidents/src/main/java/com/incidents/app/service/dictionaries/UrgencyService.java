package com.incidents.app.service.dictionaries;

import com.incidents.app.model.dictionaries.Urgency;

import java.util.List;
import java.util.Optional;

public interface UrgencyService {

    Optional<Urgency> getById(Long id);

    Urgency getByIdThrowException(Long id);

    List<Urgency> getAll();

    Urgency create();

    Urgency update();

    void delete();
}
