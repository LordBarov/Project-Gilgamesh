package com.incidents.app.controllers.dictionaries;


import com.incidents.app.dtos.response.DeleteDtoResponse;
import com.incidents.app.dtos.response.dictionaries.PriorityLevelDtoResponse;
import com.incidents.app.dtos.requests.dictionaries.PriorityLevelDtoRequest;
import com.incidents.app.mappers.dictionaries.PriorityLevelMapper;
import com.incidents.app.service.dictionaries.PriorityLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/priority-level")
@RequiredArgsConstructor
public class PriorityLevelController {

    private final PriorityLevelService priorityLevelService;


    @GetMapping("/")
    public ResponseEntity<List<PriorityLevelDtoResponse>> getAll() {
        List<PriorityLevelDtoResponse> dtoResponse = priorityLevelService.getAll().stream().map(PriorityLevelMapper::priorityLevelToDto).toList();
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriorityLevelDtoResponse> getById(@PathVariable("id") Long id) {
        PriorityLevelDtoResponse dtoResponse = PriorityLevelMapper.priorityLevelToDto(priorityLevelService.getByIdThrowException(id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PriorityLevelDtoResponse> create(@Valid @RequestBody PriorityLevelDtoRequest dtoRequest) {
        PriorityLevelDtoResponse dtoResponse = PriorityLevelMapper.priorityLevelToDto(priorityLevelService.create(dtoRequest));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<PriorityLevelDtoResponse> update(@PathVariable("id") Long id,
                                                           @Valid @RequestBody PriorityLevelDtoRequest dtoRequest) {
        PriorityLevelDtoResponse dtoResponse = PriorityLevelMapper.priorityLevelToDto(priorityLevelService.update(dtoRequest,id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteDtoResponse> delete(@PathVariable("id") Long id) {
        priorityLevelService.delete(id);
        DeleteDtoResponse deleteDtoResponse = new DeleteDtoResponse();
        deleteDtoResponse.setResponse("Вы успешно удалили Категорию");
        return new ResponseEntity<>(deleteDtoResponse,HttpStatus.OK);
    }
}
