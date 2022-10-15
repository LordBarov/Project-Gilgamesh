package com.incidents.app.service.dictionaries;


import com.incidents.app.dtos.requests.dictionaries.CategoryDtoRequest;
import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomCouldNotCreateException;
import com.incidents.app.exception.domain.CustomCouldNotDeleteException;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.dictionaries.Category;
import com.incidents.app.repository.dictionaries.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.getByIdAndExpiredDateIsNull(id);
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
        return categoryRepository.getAllByExpiredDateIsNull();
    }

    @Override
    public Category create(CategoryDtoRequest categoryDtoRequest) {
        Category category;
        try {
            category = new Category();
            category.setTitle(categoryDtoRequest.getTitle());

            return this.save(category);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public Category update(CategoryDtoRequest categoryDtoRequest, Long id) {
        Category category;
        try {
            category = this.getByIdThrowException(id);
            category.setTitle(categoryDtoRequest.getTitle());

            return this.save(category);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            this.getByIdThrowException(id).setExpiredDate(LocalDateTime.now());
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotDeleteException(CustomStatusCode.COULD_NOT_DELETE_RECORD_IN_DB.getCode());
        }
    }

    private Category save(Category category) {
        return categoryRepository.save(category);
    }
}
