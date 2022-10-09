package com.incidents.app.service.dictionaries;

import com.incidents.app.model.dictionaries.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> getById(Long id);

    Category getByIdThrowException(Long id);

    List<Category> getAll();

    Category create();

    Category update();

    void delete();
}
