package com.incidents.app.controllers.dictionaries;


import com.incidents.app.dtos.requests.dictionaries.StatusDtoResponse;
import com.incidents.app.dtos.response.DeleteDtoResponse;
import com.incidents.app.dtos.response.dictionaries.StatusDtoRequest;
import com.incidents.app.mappers.dictionaries.StatusMapper;
import com.incidents.app.service.dictionaries.PriorityLevelService;
import com.incidents.app.service.dictionaries.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;


    @GetMapping("/")
    public ResponseEntity<List<StatusDtoResponse>> getAll() {
        List<StatusDtoResponse> dtoResponse = statusService.getAll().stream().map(StatusMapper::statusToDto).toList();
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDtoResponse> getById(@PathVariable("id") Long id) {
        StatusDtoResponse dtoResponse = StatusMapper.statusToDto(statusService.getByIdThrowException(id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<StatusDtoResponse> create(@Valid @RequestBody StatusDtoRequest dtoRequest) {
        StatusDtoResponse dtoResponse = StatusMapper.statusToDto(statusService.create(dtoRequest));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<StatusDtoResponse> update(@PathVariable("id") Long id,
                                                    @Valid @RequestBody StatusDtoRequest dtoRequest) {
        StatusDtoResponse dtoResponse = StatusMapper.statusToDto(statusService.update(dtoRequest,id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteDtoResponse> delete(@PathVariable("id") Long id) {
        statusService.delete(id);
        DeleteDtoResponse deleteDtoResponse = new DeleteDtoResponse();
        deleteDtoResponse.setResponse("Вы успешно удалили Категорию");
        return new ResponseEntity<>(deleteDtoResponse,HttpStatus.OK);
    }
}
