package com.incidents.app.repository;

import com.incidents.app.model.IncidentUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncidentUsersRepository extends JpaRepository<IncidentUsers, Long> {

    List<IncidentUsers> getAllByIncidentId(Long id);
}
