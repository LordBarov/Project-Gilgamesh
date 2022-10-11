package com.clients.app.authentication;

import com.clients.app.authentication.dtos.request.TokenAuthenticationDtoRequest;
import com.clients.app.authentication.dtos.response.UserTokenAuthenticationDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@FeignClient("authentication")
public interface AuthenticationClient {

    @PostMapping("/api/v1/authentication/user/authenticate-token")
    UserTokenAuthenticationDtoResponse authenticateToken(@RequestBody TokenAuthenticationDtoRequest dtoRequest);
}
