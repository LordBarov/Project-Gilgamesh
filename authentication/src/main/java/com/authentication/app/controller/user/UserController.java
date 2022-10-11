package com.authentication.app.controller.user;

import com.authentication.app.dtos.request.UserChangeDtoRequest;
import com.authentication.app.dtos.request.UserDtoRequest;
import com.authentication.app.mappers.user.UserMapper;
import com.authentication.app.service.user.UserService;
import com.clients.app.authentication.dtos.request.TokenAuthenticationDtoRequest;
import com.clients.app.authentication.dtos.response.UserDtoResponse;
import com.clients.app.authentication.dtos.response.UserTokenAuthenticationDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authentication/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/")
    public ResponseEntity<List<UserDtoResponse>> getAll() {
        List<UserDtoResponse> users = userService.getAll().stream().map(UserMapper::userToDto).toList();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getById(@PathVariable("id") Long id) {
        UserDtoResponse userDtoResponse = UserMapper.userToDto(userService.getByIdThrowException(id));
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDtoResponse> getByUsername(@PathVariable("username") String username) {
        UserDtoResponse userDtoResponse = UserMapper.userToDto(userService.getByUsername(username));
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }


    @PostMapping("/authenticate-token")
    public UserTokenAuthenticationDtoResponse authenticateToken(@RequestBody TokenAuthenticationDtoRequest dtoRequest) {
        return userService.authenticateToken(dtoRequest);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UserDtoResponse> update(@PathVariable("id") Long id,
                                                  @Valid @RequestBody UserChangeDtoRequest userChangeDtoRequest) {
        UserDtoResponse userDtoResponse = UserMapper.userToDto(userService.update(userChangeDtoRequest, id));
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }
}
