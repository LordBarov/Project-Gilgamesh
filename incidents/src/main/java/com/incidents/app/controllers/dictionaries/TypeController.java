package com.incidents.app.controllers.dictionaries;

import com.incidents.app.dtos.requests.dictionaries.TypeDtoResponse;
import com.incidents.app.dtos.response.DeleteDtoResponse;
import com.incidents.app.dtos.response.dictionaries.TypeDtoRequest;
import com.incidents.app.mappers.dictionaries.TypeMapper;
import com.incidents.app.service.dictionaries.TagService;
import com.incidents.app.service.dictionaries.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/type")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;


    @GetMapping("/")
    public ResponseEntity<List<TypeDtoResponse>> getAll() {
        List<TypeDtoResponse> dtoResponse = typeService.getAll().stream().map(TypeMapper::typeToDto).toList();
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDtoResponse> getById(@PathVariable("id") Long id) {
        TypeDtoResponse dtoResponse = TypeMapper.typeToDto(typeService.getByIdThrowException(id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TypeDtoResponse> create(@Valid @RequestBody TypeDtoRequest dtoRequest) {
        TypeDtoResponse dtoResponse = TypeMapper.typeToDto(typeService.create(dtoRequest));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<TypeDtoResponse> update(@PathVariable("id") Long id,
                                                 @Valid @RequestBody TypeDtoRequest dtoRequest) {
        TypeDtoResponse dtoResponse = TypeMapper.typeToDto(typeService.update(dtoRequest,id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteDtoResponse> delete(@PathVariable("id") Long id) {
        typeService.delete(id);
        DeleteDtoResponse deleteDtoResponse = new DeleteDtoResponse();
        deleteDtoResponse.setResponse("Вы успешно удалили Категорию");
        return new ResponseEntity<>(deleteDtoResponse,HttpStatus.OK);
    }
}
