package com.incidents.app.controllers;

import com.incidents.app.dtos.requests.IncidentDtoRequest;
import com.incidents.app.dtos.response.IncidentDtoResponse;
import com.incidents.app.mappers.IncidentMapper;
import com.incidents.app.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/incident")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;


    @PostMapping("/create")
    public ResponseEntity<IncidentDtoResponse> create(@Valid @RequestBody IncidentDtoRequest incidentDtoRequest) {
        IncidentDtoResponse incidentDtoResponse = IncidentMapper.incidentToIncidentDto(incidentService.create(incidentDtoRequest));
        return new ResponseEntity<>(incidentDtoResponse, HttpStatus.OK);
    }
}
