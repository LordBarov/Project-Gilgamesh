package com.incidents.app.repository.user;

import com.incidents.app.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
