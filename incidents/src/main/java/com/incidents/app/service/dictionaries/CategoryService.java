package com.incidents.app.service.dictionaries;

import com.incidents.app.dtos.requests.dictionaries.CategoryDtoRequest;
import com.incidents.app.dtos.response.dictionaries.CategoryDtoResponse;
import com.incidents.app.model.dictionaries.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> getById(Long id);

    Category getByIdThrowException(Long id);

    List<Category> getAll();

    Category create(CategoryDtoRequest categoryDtoResponse);

    Category update(CategoryDtoRequest categoryDtoResponse, Long id);

    void delete(Long id);
}
