package com.incidents.app.service.dictionaries;


import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.dictionaries.Category;
import com.incidents.app.repository.dictionaries.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "category", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Category create() {
        return null;
    }

    @Override
    public Category update() {
        return null;
    }

    @Override
    public void delete() {

    }
}
