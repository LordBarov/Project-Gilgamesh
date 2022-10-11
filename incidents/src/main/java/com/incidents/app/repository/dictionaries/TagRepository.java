package com.incidents.app.repository.dictionaries;

import com.incidents.app.model.dictionaries.Tag;
import com.incidents.app.model.dictionaries.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> getByIdAndExpiredDateIsNull(Long id);

    List<Tag> getAllByExpiredDateIsNull();
}
