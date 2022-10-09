package com.emailnotification.app.controllers;


import com.emailnotification.app.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/email-notification")
@RequiredArgsConstructor
public class EmailNotificationController {

    private final EmailNotificationService emailNotificationService;


}
