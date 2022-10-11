package com.incidents.app.service.dictionaries;


import com.incidents.app.dtos.response.dictionaries.StatusDtoRequest;
import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomCouldNotCreateException;
import com.incidents.app.exception.domain.CustomCouldNotDeleteException;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.dictionaries.Status;
import com.incidents.app.repository.dictionaries.StatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class StatusServiceImplementation implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public Optional<Status> getById(Long id) {
        return statusRepository.getByIdAndExpiredDateIsNull(id);
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
        return statusRepository.getAllByExpiredDateIsNull();
    }

    @Override
    public Status create(StatusDtoRequest statusDtoRequest) {
        Status status;
        try {
            status = new Status();
            status.setTitle(statusDtoRequest.getTitle());
            status.setColor(statusDtoRequest.getColor());

            return this.save(status);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public Status update(StatusDtoRequest statusDtoRequest, Long id) {
        Status status;
        try {
            status = this.getByIdThrowException(id);
            status.setTitle(statusDtoRequest.getTitle());
            status.setColor(statusDtoRequest.getColor());

            return this.save(status);
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

    private Status save(Status status) {
        return statusRepository.save(status);
    }

}
