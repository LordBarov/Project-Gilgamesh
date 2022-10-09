package com.incidents.app.service.dictionaries;


import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.dictionaries.PriorityLevel;
import com.incidents.app.repository.dictionaries.PriorityLevelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class PriorityLevelServiceImplementation implements PriorityLevelService {

    private final PriorityLevelRepository priorityLevelRepository;

    @Override
    public Optional<PriorityLevel> getById(Long id) {
        return priorityLevelRepository.findById(id);
    }

    @Override
    public PriorityLevel getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "priority", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<PriorityLevel> getAll() {
        return null;
    }

    @Override
    public PriorityLevel create() {
        return null;
    }

    @Override
    public PriorityLevel update() {
        return null;
    }

    @Override
    public void delete() {

    }
}
