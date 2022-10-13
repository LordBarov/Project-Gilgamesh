package com.incidents.app.dtos.response;

import com.clients.app.authentication.dtos.response.UserDtoResponse;
import com.incidents.app.dtos.response.dictionaries.StatusDtoResponse;
import com.incidents.app.dtos.response.dictionaries.UrgencyDtoResponse;
import lombok.Data;

import java.util.List;

@Data
public class TaskDtoResponse {

    private String title;

    private String description;

    private StatusDtoResponse statusDtoResponse;

    private UrgencyDtoResponse urgencyDtoResponse;

    private Long incidentId;

    private List<UserDtoResponse> users;
}
