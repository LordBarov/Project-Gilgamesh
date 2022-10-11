package com.incidents.app.controllers.dictionaries;

import com.incidents.app.dtos.requests.dictionaries.TagDtoResponse;
import com.incidents.app.dtos.response.DeleteDtoResponse;
import com.incidents.app.dtos.response.dictionaries.TagDtoRequest;
import com.incidents.app.mappers.dictionaries.TagMapper;
import com.incidents.app.service.dictionaries.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/urgency")
@RequiredArgsConstructor
public class UrgencyController {

    private final TagService tagService;


    @GetMapping("/")
    public ResponseEntity<List<TagDtoResponse>> getAll() {
        List<TagDtoResponse> dtoResponse = tagService.getAll().stream().map(TagMapper::tagToDto).toList();
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDtoResponse> getById(@PathVariable("id") Long id) {
        TagDtoResponse dtoResponse = TagMapper.tagToDto(tagService.getByIdThrowException(id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TagDtoResponse> create(@Valid @RequestBody TagDtoRequest dtoRequest) {
        TagDtoResponse dtoResponse = TagMapper.tagToDto(tagService.create(dtoRequest));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<TagDtoResponse> update(@PathVariable("id") Long id,
                                                 @Valid @RequestBody TagDtoRequest dtoRequest) {
        TagDtoResponse dtoResponse = TagMapper.tagToDto(tagService.update(dtoRequest,id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteDtoResponse> delete(@PathVariable("id") Long id) {
        tagService.delete(id);
        DeleteDtoResponse deleteDtoResponse = new DeleteDtoResponse();
        deleteDtoResponse.setResponse("Вы успешно удалили Категорию");
        return new ResponseEntity<>(deleteDtoResponse,HttpStatus.OK);
    }
}
