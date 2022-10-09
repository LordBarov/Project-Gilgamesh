package com.emailnotification.app.service;

import com.emailnotification.app.model.EmailType;

import java.util.List;
import java.util.Optional;

public interface EmailTypeService {

    Optional<EmailType> getById(Long id);

    EmailType getByIdThrowException(Long id);

    List<EmailType> getAll();

    EmailType create();

    EmailType update();

    void delete();
}
