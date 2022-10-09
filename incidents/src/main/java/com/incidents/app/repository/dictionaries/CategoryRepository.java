package com.incidents.app.repository.dictionaries;

import com.incidents.app.model.dictionaries.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
