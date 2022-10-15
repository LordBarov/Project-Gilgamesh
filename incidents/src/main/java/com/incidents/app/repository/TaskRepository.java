package com.incidents.app.repository;

import com.incidents.app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> getAllByIncidentIdOrderByCreatedDateDesc(Long incidentId);
}
