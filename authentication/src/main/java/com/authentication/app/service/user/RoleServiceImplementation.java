package com.authentication.app.service.user;


import com.authentication.app.exception.CustomStatusCode;
import com.authentication.app.exception.ExceptionDescription;
import com.authentication.app.exception.domain.CustomNotFoundException;
import com.authentication.app.model.user.Role;
import com.authentication.app.repository.user.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Log4j2
public class RoleServiceImplementation implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> getById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "role", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public Role getByTitle(String title) {
        return roleRepository.getRoleByTitle(title).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "role", "id", title));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Role create() {
        return null;
    }

    @Override
    public Role update() {
        return null;
    }

    @Override
    public void delete() {

    }
}
