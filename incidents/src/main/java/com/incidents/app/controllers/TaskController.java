package com.incidents.app.controllers;

import com.clients.app.authentication.dtos.response.UserDtoResponse;
import com.incidents.app.dtos.requests.TaskDtoRequest;
import com.incidents.app.dtos.response.TaskDtoResponse;
import com.incidents.app.mappers.TaskMapper;
import com.incidents.app.service.TaskService;
import com.incidents.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/api/v1/incident/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<TaskDtoResponse> create(@Valid @RequestBody TaskDtoRequest taskDtoRequest) {
        List<UserDtoResponse> users = userService.getAllByIdList(taskDtoRequest.getUserIds());
        TaskDtoResponse taskDtoResponse = TaskMapper.taskToDto(taskService.create(taskDtoRequest),users);
        return new ResponseEntity<>(taskDtoResponse, HttpStatus.OK);
    }
}
