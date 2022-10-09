package com.incidents.app.repository.dictionaries;

import com.incidents.app.model.dictionaries.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
