package com.authentication.app.controller.user;

import com.authentication.app.dtos.request.LoginDtoRequest;
import com.authentication.app.dtos.request.UserDtoRequest;
import com.authentication.app.mappers.user.UserMapper;
import com.authentication.app.service.user.UserService;
import com.clients.app.authentication.dtos.response.UserDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDtoResponse> authenticate(@Valid @RequestBody LoginDtoRequest loginDtoRequest, HttpServletRequest httpServletRequest) {
        return userService.authenticate(loginDtoRequest,httpServletRequest);
    }


    @PostMapping("/registration")
    public ResponseEntity<UserDtoResponse> registration(@Valid @RequestBody UserDtoRequest userDtoRequest) {
        UserDtoResponse userDtoResponse = UserMapper.userToDto(userService.register(userDtoRequest));
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }
}
