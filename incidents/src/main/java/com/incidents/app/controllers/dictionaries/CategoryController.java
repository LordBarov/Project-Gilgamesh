package com.incidents.app.controllers.dictionaries;


import com.incidents.app.dtos.requests.dictionaries.CategoryDtoRequest;
import com.incidents.app.dtos.response.DeleteDtoResponse;
import com.incidents.app.dtos.response.dictionaries.CategoryDtoResponse;
import com.incidents.app.mappers.dictionaries.CategoryMapper;
import com.incidents.app.service.dictionaries.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/")
    public ResponseEntity<List<CategoryDtoResponse>> getAll() {
        List<CategoryDtoResponse> dtoResponse = categoryService.getAll().stream().map(CategoryMapper::categoryToDto).toList();
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDtoResponse> getById(@PathVariable("id") Long id) {
        CategoryDtoResponse dtoResponse = CategoryMapper.categoryToDto(categoryService.getByIdThrowException(id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDtoResponse> create(@Valid @RequestBody CategoryDtoRequest dtoRequest) {
        CategoryDtoResponse dtoResponse = CategoryMapper.categoryToDto(categoryService.create(dtoRequest));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<CategoryDtoResponse> update(@PathVariable("id") Long id,
                                                      @Valid @RequestBody CategoryDtoRequest dtoRequest) {
        CategoryDtoResponse dtoResponse = CategoryMapper.categoryToDto(categoryService.update(dtoRequest,id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteDtoResponse> delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        DeleteDtoResponse deleteDtoResponse = new DeleteDtoResponse();
        deleteDtoResponse.setResponse("Вы успешно удалили Категорию");
        return new ResponseEntity<>(deleteDtoResponse,HttpStatus.OK);
    }
}
