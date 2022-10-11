package com.authentication.app.service.user;


import com.authentication.app.dtos.request.LoginDtoRequest;
import com.authentication.app.dtos.request.UserChangeDtoRequest;
import com.authentication.app.dtos.request.UserDtoRequest;

import com.authentication.app.model.user.User;
import com.clients.app.authentication.dtos.request.TokenAuthenticationDtoRequest;
import com.clients.app.authentication.dtos.response.UserDtoResponse;
import com.clients.app.authentication.dtos.response.UserTokenAuthenticationDtoResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getById(Long id);

    User getByIdThrowException(Long id);

    User getByUsername(String username);

    List<User> getAll();

    List<User> getAllByListOfIds(List<Long> ids);

    User register(UserDtoRequest dtoRequest);

    User update(UserChangeDtoRequest dtoRequest, Long id);

    void delete(Long id);

    ResponseEntity<UserDtoResponse> authenticate(LoginDtoRequest loginDtoRequest, HttpServletRequest httpServletRequest);

    UserTokenAuthenticationDtoResponse authenticateToken(TokenAuthenticationDtoRequest dtoRequest);
}
