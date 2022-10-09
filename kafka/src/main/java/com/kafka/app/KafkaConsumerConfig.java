package com.kafka.app;

import com.clients.app.emailnotification.EmailNotificationDtoRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    @Value("localhost:29092")
    private String bootstrapService;


    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapService);
        return props;
    }

    @Bean
    public ConsumerFactory<String, EmailNotificationDtoRequest> consumerFactory() {
        JsonDeserializer<EmailNotificationDtoRequest> deserializer =new JsonDeserializer<>();
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfig(),new StringDeserializer(), deserializer);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, EmailNotificationDtoRequest>> factory(ConsumerFactory<String, EmailNotificationDtoRequest> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, EmailNotificationDtoRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
