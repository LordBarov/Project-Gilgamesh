package com.incidents.app.service.dictionaries;


import com.incidents.app.dtos.requests.dictionaries.UrgencyDtoRequest;
import com.incidents.app.dtos.response.dictionaries.UrgencyDtoResponse;
import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomCouldNotCreateException;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.dictionaries.Urgency;
import com.incidents.app.repository.dictionaries.UrgencyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class UrgencyServiceImplementation implements UrgencyService {

    private final UrgencyRepository urgencyRepository;

    @Override
    public Optional<Urgency> getById(Long id) {
        return urgencyRepository.findById(id);
    }

    @Override
    public Urgency getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "urgency", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<Urgency> getAll() {
        return null;
    }

    @Override
    public Urgency create(UrgencyDtoRequest dtoRequest) {
        Urgency urgency;
        try {
            urgency = new Urgency();
            urgency.setTitle(dtoRequest.getTitle());
            urgency.setColor(dtoRequest.getColor());

            return this.save(urgency);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public Urgency update(UrgencyDtoRequest dtoRequest, Long id) {
        Urgency urgency;
        try {
            urgency = this.getByIdThrowException(id);
            urgency.setTitle(dtoRequest.getTitle());
            urgency.setColor(dtoRequest.getColor());

            return this.save(urgency);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            urgencyRepository.delete(this.getByIdThrowException(id));
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    private Urgency save(Urgency urgency) {
        return urgencyRepository.save(urgency);
    }
}
