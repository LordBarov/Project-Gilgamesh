package com.authentication.app.service.user;


import com.authentication.app.model.user.Privilege;

import java.util.List;
import java.util.Optional;

public interface PrivilegeService {

    Optional<Privilege> getById(Long id);

    Privilege getByIdThrowException(Long id);

    List<Privilege> getAll();

    Privilege create();

    Privilege update();

    void delete();
}
