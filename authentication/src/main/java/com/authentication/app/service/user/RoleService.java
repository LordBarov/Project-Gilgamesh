package com.authentication.app.service.user;


import com.authentication.app.model.user.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> getById(Long id);

    Role getByIdThrowException(Long id);

    Role getByTitle(String title);

    List<Role> getAll();

    Role create();

    Role update();

    void delete();
}
