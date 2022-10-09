package com.incidents.app.service.user;

import com.incidents.app.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getById(Long id);

    User getByIdThrowException(Long id);

    List<User> getAll();

    List<User> getAllByListOfIds(List<Long> ids);

    User create();

    User update();

    void delete();
}
