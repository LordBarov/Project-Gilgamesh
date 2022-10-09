package com.incidents.app.repository.dictionaries;

import com.incidents.app.model.dictionaries.PriorityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityLevelRepository extends JpaRepository<PriorityLevel, Long> {
}
