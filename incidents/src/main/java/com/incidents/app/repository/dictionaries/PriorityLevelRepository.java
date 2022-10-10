package com.incidents.app.repository.dictionaries;

import com.incidents.app.model.dictionaries.PriorityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PriorityLevelRepository extends JpaRepository<PriorityLevel, Long> {

    Optional<PriorityLevel> getByIdAndExpiredDateIsNull(Long id);

    List<PriorityLevel> getAllByExpiredDateIsNull();
}
