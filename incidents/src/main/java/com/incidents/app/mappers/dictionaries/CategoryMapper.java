package com.incidents.app.mappers.dictionaries;

import com.incidents.app.dtos.response.dictionaries.CategoryDtoResponse;
import com.incidents.app.model.dictionaries.Category;

public class CategoryMapper {

    public static CategoryDtoResponse categoryToDto(Category category) {
        CategoryDtoResponse categoryDtoResponse = new CategoryDtoResponse();
        categoryDtoResponse.setTitle(category.getTitle());
        return categoryDtoResponse;
    }
}
