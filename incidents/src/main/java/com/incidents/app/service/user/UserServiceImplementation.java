package com.incidents.app.service.user;

import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.user.User;
import com.incidents.app.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Log4j2
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

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
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getAllByListOfIds(List<Long> ids) {
        return ids.stream().map(this::getByIdThrowException).collect(Collectors.toList());
    }

    @Override
    public User create() {
        return null;
    }

    @Override
    public User update() {
        return null;
    }

    @Override
    public void delete() {

    }
}
