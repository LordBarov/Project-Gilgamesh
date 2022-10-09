package com.incidents.app.service.dictionaries;


import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.dictionaries.Status;
import com.incidents.app.repository.dictionaries.StatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class StatusServiceImplementation implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public Optional<Status> getById(Long id) {
        return statusRepository.findById(id);
    }

    @Override
    public Status getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "status", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<Status> getAll() {
        return null;
    }

    @Override
    public Status create() {
        return null;
    }

    @Override
    public Status update() {
        return null;
    }

    @Override
    public void delete() {

    }
}
