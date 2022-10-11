package com.incidents.app.repository.dictionaries;

import com.incidents.app.model.dictionaries.Type;
import com.incidents.app.model.dictionaries.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> getByIdAndExpiredDateIsNull(Long id);

    List<Type> getAllByExpiredDateIsNull();
}
