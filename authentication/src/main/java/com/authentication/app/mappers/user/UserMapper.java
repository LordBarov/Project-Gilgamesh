package com.authentication.app.mappers.user;


import com.authentication.app.model.user.User;
import com.clients.app.authentication.dtos.response.UserDtoResponse;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDtoResponse userToDto(User user) {
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setUsername(user.getUsername());
        userDtoResponse.setEmail(user.getEmail());
        userDtoResponse.setRoles(user.getRoles().stream().map(RoleMapper::roleToDto).collect(Collectors.toList()));
        return userDtoResponse;
    }
}
