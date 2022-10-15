package com.incidents.app.controllers;

import com.clients.app.authentication.dtos.response.UserDtoResponse;
import com.incidents.app.dtos.requests.IncidentDtoRequest;
import com.incidents.app.dtos.response.incident.IncidentCreateDtoResponse;
import com.incidents.app.mappers.incident.IncidentCreateMapper;
import com.incidents.app.service.IncidentService;
import com.incidents.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/incident/service")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;
    private final UserService userService;



    @PostMapping("/create")
    public ResponseEntity<IncidentCreateDtoResponse> create(@Valid @RequestBody IncidentDtoRequest incidentDtoRequest) {
        List<UserDtoResponse> users = userService.getAllByIdList(incidentDtoRequest.getUserIds());
        IncidentCreateDtoResponse incidentDtoResponse = IncidentCreateMapper.incidentCreateToDto(incidentService.create(incidentDtoRequest),users);
        return new ResponseEntity<>(incidentDtoResponse, HttpStatus.OK);
    }
}
