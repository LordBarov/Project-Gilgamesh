package com.incidents.app.service;

import com.clients.app.authentication.dtos.response.UserDtoResponse;

import java.util.List;

public interface UserService {

    List<UserDtoResponse> getAllByIdList(List<Long> ids);

    boolean doesExist(Long id);
}
