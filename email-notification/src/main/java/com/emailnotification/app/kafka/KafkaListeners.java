package com.emailnotification.app.kafka;

import com.clients.app.emailnotification.EmailNotificationDtoRequest;
import com.emailnotification.app.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {

    private final EmailNotificationService emailNotificationService;

    @KafkaListener(topics = "gilgamesh-notification", groupId = "groupId", containerFactory = "factory")
    void listener(EmailNotificationDtoRequest dtoRequest) {
        System.out.println("Listener recieved data: " + dtoRequest + " :D");
        emailNotificationService.create(dtoRequest);
    }
}
