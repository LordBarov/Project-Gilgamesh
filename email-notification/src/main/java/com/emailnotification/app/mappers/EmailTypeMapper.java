package com.emailnotification.app.mappers;

import com.clients.app.emailnotification.EmailTypeDtoResponse;
import com.emailnotification.app.model.EmailType;

public class EmailTypeMapper {

    public static EmailTypeDtoResponse emailTypeToDto(EmailType emailType) {
        EmailTypeDtoResponse dtoResponse = new EmailTypeDtoResponse();
        dtoResponse.setTitle(emailType.getTitle());
        return dtoResponse;
    }
}
