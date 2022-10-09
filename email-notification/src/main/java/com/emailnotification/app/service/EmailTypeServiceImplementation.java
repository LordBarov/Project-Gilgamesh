package com.emailnotification.app.service;


import com.emailnotification.app.exception.CustomStatusCode;
import com.emailnotification.app.exception.ExceptionDescription;
import com.emailnotification.app.exception.domain.CustomNotFoundException;
import com.emailnotification.app.model.EmailType;
import com.emailnotification.app.repository.EmailTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class EmailTypeServiceImplementation implements EmailTypeService{

    private final EmailTypeRepository emailTypeRepository;


    @Override
    public Optional<EmailType> getById(Long id) {
        return emailTypeRepository.findById(id);
    }

    @Override
    public EmailType getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "status", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<EmailType> getAll() {
        return null;
    }

    @Override
    public EmailType create() {
        return null;
    }

    @Override
    public EmailType update() {
        return null;
    }

    @Override
    public void delete() {

    }
}
