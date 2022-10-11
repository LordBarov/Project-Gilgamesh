package com.incidents.app.repository.dictionaries;

import com.incidents.app.model.dictionaries.Status;
import com.incidents.app.model.dictionaries.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Optional<Status> getByIdAndExpiredDateIsNull(Long id);

    List<Status> getAllByExpiredDateIsNull();
}
