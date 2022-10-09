package com.incidents.app.repository;

import com.incidents.app.model.TaskUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskUsersRepository extends JpaRepository<TaskUsers, Long> {
}
