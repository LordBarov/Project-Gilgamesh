package com.incidents.app.security;

import com.clients.app.authentication.AuthenticationClient;
import com.clients.app.authentication.dtos.request.TokenAuthenticationDtoRequest;
import com.clients.app.authentication.dtos.response.PrivilegeDtoResponse;
import com.clients.app.authentication.dtos.response.RoleDtoResponse;
import com.clients.app.authentication.dtos.response.UserTokenAuthenticationDtoResponse;
import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.domain.JwtTokenNotValidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.incidents.app.security.SecurityConstants.TOKEN_PREFIX;
import static com.incidents.app.security.SecurityConstants.VALID_IP_HEADER_CANDIDATES;

@Log4j2
@RequiredArgsConstructor
@Component
public class JWTTokenAuthenticator extends OncePerRequestFilter {

    private final AuthenticationClient authenticationClient;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("started");
        try {
            String userIp = getIpFromClient(request);
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            String token = authorizationHeader.substring(TOKEN_PREFIX.length());
            TokenAuthenticationDtoRequest dtoRequest = new TokenAuthenticationDtoRequest();
            dtoRequest.setToken(token);
            dtoRequest.setUserIp(userIp);
            UserTokenAuthenticationDtoResponse dtoResponse = authenticationClient.authenticateToken(dtoRequest);
            log.info("dto response: {}", dtoResponse);
            String username = dtoResponse.getUsername();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, this.getAuthorities(dtoResponse.getRoles()));
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        catch (Exception e) {
            log.error(e);
        }
        filterChain.doFilter(request,response);
    }

    public String getIpFromClient(HttpServletRequest request) {
        for (String header : VALID_IP_HEADER_CANDIDATES) {
            String ipAddress = request.getHeader(header);
            if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
                return ipAddress;
            }
        }
        return request.getRemoteAddr();
    }

    private List<String> getPrivileges(Collection<RoleDtoResponse> roles) {
        List<String> privileges = new ArrayList<>();
        List<PrivilegeDtoResponse> collection = new ArrayList<>();
        for (RoleDtoResponse role : roles) {
            privileges.add(role.getTitle());
            collection.addAll(role.getPrivileges());
        }
        for (PrivilegeDtoResponse item : collection) {
            privileges.add(item.getTitle());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege:privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    private Collection<? extends GrantedAuthority> getAuthorities(Collection<RoleDtoResponse> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }
}
