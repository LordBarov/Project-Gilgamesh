package com.emailnotification.app.controllers;

import com.emailnotification.app.service.EmailTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email-type")
@RequiredArgsConstructor
@Log4j2
public class EmailTypeController {

    private final EmailTypeService emailTypeService;

}
