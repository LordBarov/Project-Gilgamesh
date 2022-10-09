package com.incidents.app.service.user;

import com.incidents.app.model.user.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> getById(Long id);

    Role getByIdThrowException(Long id);

    List<Role> getAll();

    Role create();

    Role update();

    void delete();
}
