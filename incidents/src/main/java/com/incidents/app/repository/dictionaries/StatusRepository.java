package com.incidents.app.repository.dictionaries;

import com.incidents.app.model.dictionaries.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
