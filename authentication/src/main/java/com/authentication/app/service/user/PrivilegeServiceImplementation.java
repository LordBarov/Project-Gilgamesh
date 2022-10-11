package com.authentication.app.service.user;



import com.authentication.app.exception.CustomStatusCode;
import com.authentication.app.exception.ExceptionDescription;
import com.authentication.app.exception.domain.CustomNotFoundException;
import com.authentication.app.model.user.Privilege;
import com.authentication.app.repository.user.PrivilegeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class PrivilegeServiceImplementation implements PrivilegeService{

    private final PrivilegeRepository privilegeRepository;

    @Override
    public Optional<Privilege> getById(Long id) {
        return privilegeRepository.findById(id);
    }

    @Override
    public Privilege getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "privilege", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<Privilege> getAll() {
        return null;
    }

    @Override
    public Privilege create() {
        return null;
    }

    @Override
    public Privilege update() {
        return null;
    }

    @Override
    public void delete() {

    }
}
