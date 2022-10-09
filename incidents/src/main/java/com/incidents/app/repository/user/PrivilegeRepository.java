package com.incidents.app.repository.user;

import com.incidents.app.model.user.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
