package com.incidents.app.service.dictionaries;


import com.incidents.app.dtos.requests.dictionaries.PriorityLevelDtoRequest;
import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomCouldNotCreateException;
import com.incidents.app.exception.domain.CustomCouldNotDeleteException;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.dictionaries.PriorityLevel;
import com.incidents.app.repository.dictionaries.PriorityLevelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class PriorityLevelServiceImplementation implements PriorityLevelService {

    private final PriorityLevelRepository priorityLevelRepository;

    @Override
    public Optional<PriorityLevel> getById(Long id) {
        return priorityLevelRepository.getByIdAndExpiredDateIsNull(id);
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
        return priorityLevelRepository.getAllByExpiredDateIsNull();
    }

    @Override
    public PriorityLevel create(PriorityLevelDtoRequest dtoRequest) {
        PriorityLevel priorityLevel;
        try {
            priorityLevel = new PriorityLevel();
            priorityLevel.setTitle(dtoRequest.getTitle());
            priorityLevel.setColor(dtoRequest.getColor());

            return this.save(priorityLevel);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public PriorityLevel update(PriorityLevelDtoRequest dtoRequest, Long id) {
        PriorityLevel priorityLevel;
        try {
            priorityLevel = this.getByIdThrowException(id);
            priorityLevel.setTitle(dtoRequest.getTitle());
            priorityLevel.setColor(dtoRequest.getColor());

            return this.save(priorityLevel);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            this.getByIdThrowException(id).setExpiredDate(LocalDateTime.now());
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotDeleteException(CustomStatusCode.COULD_NOT_DELETE_RECORD_IN_DB.getCode());
        }
    }

    private PriorityLevel save(PriorityLevel priorityLevel) {
        return priorityLevelRepository.save(priorityLevel);
    }
}
