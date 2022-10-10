package com.incidents.app.repository.dictionaries;

import com.incidents.app.model.dictionaries.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> getByIdAndExpiredDateIsNull(Long id);

    List<Category> getAllByExpiredDateIsNull();
}
