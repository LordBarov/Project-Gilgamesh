package com.incidents.app.repository.dictionaries;

import com.incidents.app.model.dictionaries.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
