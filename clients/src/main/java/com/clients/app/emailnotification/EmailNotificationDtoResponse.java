package com.clients.app.emailnotification;

import lombok.Data;

@Data
public class EmailNotificationDtoResponse {

    private String title;

    private String description;

    private EmailTypeDtoResponse emailType;
}
