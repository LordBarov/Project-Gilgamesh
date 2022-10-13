package com.incidents.app.controllers;

import com.clients.app.authentication.AuthenticationClient;
import com.clients.app.authentication.dtos.request.UserIdDtoRequest;
import com.clients.app.authentication.dtos.response.UserDtoResponse;
import com.incidents.app.dtos.requests.IncidentDtoRequest;
import com.incidents.app.dtos.response.incident.IncidentCreateDtoResponse;
import com.incidents.app.dtos.response.incident.IncidentDtoResponse;
import com.incidents.app.mappers.incident.IncidentCreateMapper;
import com.incidents.app.mappers.incident.IncidentMapper;
import com.incidents.app.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/incident")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;
    private final AuthenticationClient authenticationClient;



    @PostMapping("/create")
    public ResponseEntity<IncidentCreateDtoResponse> create(@Valid @RequestBody IncidentDtoRequest incidentDtoRequest) {
        List<UserDtoResponse> users = authenticationClient.getAllByIdList(new UserIdDtoRequest(incidentDtoRequest.getUserIds()));
        IncidentCreateDtoResponse incidentDtoResponse = IncidentCreateMapper.incidentCreateToDto(incidentService.create(incidentDtoRequest),users);
        return new ResponseEntity<>(incidentDtoResponse, HttpStatus.OK);
    }
}
