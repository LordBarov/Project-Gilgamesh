package com.clients.app.authentication;

import com.clients.app.authentication.dtos.request.TokenAuthenticationDtoRequest;
import com.clients.app.authentication.dtos.request.UserIdDtoRequest;
import com.clients.app.authentication.dtos.response.UserDtoResponse;
import com.clients.app.authentication.dtos.response.UserTokenAuthenticationDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("authentication")
public interface AuthenticationClient {

    @PostMapping("/api/v1/authentication/user/authenticate-token")
    UserTokenAuthenticationDtoResponse authenticateToken(@RequestBody TokenAuthenticationDtoRequest dtoRequest);

    @GetMapping("/api/v1/authentication/user/{id}/check")
    boolean doesExist(@PathVariable("id") Long id);

    @PostMapping("/api/v1/authentication/user//list")
    List<UserDtoResponse> getAllByIdList(@RequestBody UserIdDtoRequest userIdDtoRequest);
}
