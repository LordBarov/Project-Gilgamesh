package com.incidents.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.incidents.app",
                "com.kafka.app"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.clients.app"
)
public class IncidentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncidentsApplication.class, args);
    }
}
