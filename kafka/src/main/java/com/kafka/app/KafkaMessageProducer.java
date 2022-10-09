package com.kafka.app;

import com.clients.app.emailnotification.EmailNotificationDtoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageProducer {

    private final KafkaTemplate<String, EmailNotificationDtoRequest> kafkaTemplate;

    public void sendNotification(EmailNotificationDtoRequest dtoRequest) {
        kafkaTemplate.send("gilgamesh-notification", dtoRequest);
    }

}