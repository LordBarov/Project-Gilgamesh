package com.emailnotification.app.service;

import com.clients.app.emailnotification.EmailNotificationDtoRequest;
import com.emailnotification.app.model.EmailNotification;

import java.util.List;
import java.util.Optional;

public interface EmailNotificationService {
    Optional<EmailNotification> getById(Long id);

    EmailNotification getByIdThrowException(Long id);

    List<EmailNotification> getAll();

    EmailNotification create(EmailNotificationDtoRequest dtoRequest);

    EmailNotification update();

    void delete();
}
