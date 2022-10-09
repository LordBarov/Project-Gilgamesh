package com.incidents.app.service.dictionaries;

import com.incidents.app.model.dictionaries.PriorityLevel;

import java.util.List;
import java.util.Optional;

public interface PriorityLevelService {

    Optional<PriorityLevel> getById(Long id);

    PriorityLevel getByIdThrowException(Long id);

    List<PriorityLevel> getAll();

    PriorityLevel create();

    PriorityLevel update();

    void delete();
}
