package com.incidents.app.repository.dictionaries;

import com.incidents.app.model.dictionaries.Urgency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrgencyRepository extends JpaRepository<Urgency, Long> {
}
