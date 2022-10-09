package com.emailnotification.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.emailnotification.app",
                "com.kafka.app"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.clients.app"
)
public class EmailNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailNotificationApplication.class, args);
    }
}
