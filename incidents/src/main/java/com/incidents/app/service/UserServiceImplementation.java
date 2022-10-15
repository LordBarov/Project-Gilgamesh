package com.incidents.app.service;

import com.clients.app.authentication.AuthenticationClient;
import com.clients.app.authentication.dtos.request.UserIdDtoRequest;
import com.clients.app.authentication.dtos.response.UserDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{

    private final AuthenticationClient authenticationClient;

    @Override
    public List<UserDtoResponse> getAllByIdList(List<Long> ids) {
        return authenticationClient.getAllByIdList(new UserIdDtoRequest(ids));
    }

    @Override
    public boolean doesExist(Long id) {
        return authenticationClient.doesExist(id);
    }
}
