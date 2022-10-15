package com.emailnotification.app.service;


import com.clients.app.emailnotification.EmailNotificationDtoRequest;
import com.emailnotification.app.exception.CustomStatusCode;
import com.emailnotification.app.exception.domain.CustomCouldNotCreateException;
import com.emailnotification.app.model.EmailNotification;
import com.emailnotification.app.repository.EmailNotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class EmailNotificationServiceImplementation implements EmailNotificationService{

    private final EmailNotificationRepository emailNotificationRepository;
    private final EmailTypeService emailTypeService;

    @Override
    public Optional<EmailNotification> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public EmailNotification getByIdThrowException(Long id) {
        return null;
    }

    @Override
    public List<EmailNotification> getAll() {
        return null;
    }

    @Override
    public EmailNotification create(EmailNotificationDtoRequest dtoRequest) {
        EmailNotification emailNotification;
        try {
            emailNotification = new EmailNotification();
            emailNotification.setTitle(dtoRequest.getTitle());
            emailNotification.setDescription(dtoRequest.getDescription());
            emailNotification.setEmailType(emailTypeService.getByIdThrowException(dtoRequest.getEmailType()));
            return this.save(emailNotification);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public EmailNotification update() {
        return null;
    }

    @Override
    public void delete() {

    }

    private EmailNotification save(EmailNotification emailNotification) {
        return emailNotificationRepository.save(emailNotification);
    }
}
