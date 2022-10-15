package com.authentication.app.service.user;


import com.authentication.app.dtos.request.LoginDtoRequest;
import com.authentication.app.dtos.request.UserChangeDtoRequest;
import com.authentication.app.dtos.request.UserDtoRequest;
import com.authentication.app.exception.CustomStatusCode;
import com.authentication.app.exception.ExceptionDescription;
import com.authentication.app.exception.domain.CustomCouldNotCreateException;
import com.authentication.app.exception.domain.CustomNotFoundException;
import com.authentication.app.exception.domain.JwtTokenNotValidException;
import com.authentication.app.mappers.user.RoleMapper;
import com.authentication.app.mappers.user.UserMapper;
import com.authentication.app.model.user.Role;
import com.authentication.app.model.user.User;
import com.authentication.app.repository.user.UserRepository;
import com.authentication.app.security.CustomUserDetails;
import com.authentication.app.security.JWTTokenProvider;
import com.authentication.app.security.SecurityConstants;
import com.clients.app.authentication.dtos.request.TokenAuthenticationDtoRequest;
import com.clients.app.authentication.dtos.response.UserDtoResponse;
import com.clients.app.authentication.dtos.response.UserTokenAuthenticationDtoResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Log4j2
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService,@Lazy AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "user", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getUserByUsername(username).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "user", "username", username));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getAllByListOfIds(List<Long> ids) {
        return ids.stream().map(this::getByIdThrowException).collect(Collectors.toList());
    }

    @Override
    public User register(UserDtoRequest dtoRequest) {
        User user;
        try {
            user = new User();
            user.setUsername(dtoRequest.getUsername());
            user.setEmail(dtoRequest.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(dtoRequest.getPassword()));

            Role role = roleService.getByTitle("ROLE_USER");
            user.setRoles(new ArrayList<>(List.of(role)));

            if (dtoRequest.getAvatar() != null) {
                try {
                    user.setImage(dtoRequest.getAvatar().getBytes());
                } catch (IOException e) {
                    log.error(e);
                }
            }
            else {
                user.setImage(null);
            }

            return this.save(user);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public User update(UserChangeDtoRequest dtoRequest, Long id) {
        User user;
        try {
            user = this.getByIdThrowException(id);
            user.setUsername(dtoRequest.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(dtoRequest.getPassword()));

            if (dtoRequest.getAvatar() != null) {
                try {
                    user.setImage(dtoRequest.getAvatar().getBytes());
                } catch (IOException e) {
                    log.error(e);
                }
            }
            else {
                user.setImage(null);
            }

            return this.save(user);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(this.getByIdThrowException(id));
    }


    @Override
    public ResponseEntity<UserDtoResponse> authenticate(LoginDtoRequest loginDtoRequest, HttpServletRequest httpServletRequest) {
        final String username = loginDtoRequest.getUsername();
        final String password = loginDtoRequest.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user = this.getByUsername(username);
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String clientIp = jwtTokenProvider.getIpFromClient(httpServletRequest);
        HttpHeaders token =getJwtHeader(userDetails,clientIp);
        UserDtoResponse userDtoResponse = UserMapper.userToDto(user);

        return new ResponseEntity<>(userDtoResponse,token, HttpStatus.OK);
    }

    @Override
    public UserTokenAuthenticationDtoResponse authenticateToken(TokenAuthenticationDtoRequest dtoRequest) {
        UserTokenAuthenticationDtoResponse dtoResponse = new UserTokenAuthenticationDtoResponse();
        String username;
        try {
            username = jwtTokenProvider.getSubject(dtoRequest.getToken());
            if (jwtTokenProvider.isTokenValid(username,dtoRequest.getToken(),dtoRequest.getUserIp())) {
                User user = this.getByUsername(username);
                dtoResponse.setUsername(username);
                dtoResponse.setRoles(user.getRoles().stream().map(RoleMapper::roleToDto).toList());

                return dtoResponse;
            }
        }
        catch (Exception e) {
            throw new JwtTokenNotValidException(CustomStatusCode.JWT_TOKEN_NOT_VALID.getCode());
        }
        throw new JwtTokenNotValidException(CustomStatusCode.JWT_TOKEN_NOT_VALID.getCode());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(getByUsername(username));
    }

    @Override
    public boolean doesExist(Long id) {
        return userRepository.existsById(id);
    }

    private HttpHeaders getJwtHeader(CustomUserDetails userPrincipal, String ipFromClient) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstants.JWT_TOKEN_HEADER, jwtTokenProvider.generateToken(userPrincipal, ipFromClient));
        return httpHeaders;
    }


    private User save(User user) {
        return userRepository.save(user);
    }

}
