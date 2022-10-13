package com.incidents.app.controllers.dictionaries;

import com.incidents.app.dtos.requests.dictionaries.UrgencyDtoRequest;
import com.incidents.app.dtos.response.DeleteDtoResponse;
import com.incidents.app.dtos.response.dictionaries.UrgencyDtoResponse;
import com.incidents.app.mappers.dictionaries.UrgencyMapper;
import com.incidents.app.service.dictionaries.UrgencyService;
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

    private final UrgencyService urgencyService;


    @GetMapping("/")
    public ResponseEntity<List<UrgencyDtoResponse>> getAll() {
        List<UrgencyDtoResponse> dtoResponse = urgencyService.getAll().stream().map(UrgencyMapper::urgencyToDto).toList();
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UrgencyDtoResponse> getById(@PathVariable("id") Long id) {
        UrgencyDtoResponse dtoResponse = UrgencyMapper.urgencyToDto(urgencyService.getByIdThrowException(id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UrgencyDtoResponse> create(@Valid @RequestBody UrgencyDtoRequest dtoRequest) {
        UrgencyDtoResponse dtoResponse = UrgencyMapper.urgencyToDto(urgencyService.create(dtoRequest));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UrgencyDtoResponse> update(@PathVariable("id") Long id,
                                                 @Valid @RequestBody UrgencyDtoRequest dtoRequest) {
        UrgencyDtoResponse dtoResponse = UrgencyMapper.urgencyToDto(urgencyService.update(dtoRequest,id));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteDtoResponse> delete(@PathVariable("id") Long id) {
        urgencyService.delete(id);
        DeleteDtoResponse deleteDtoResponse = new DeleteDtoResponse();
        deleteDtoResponse.setResponse("Вы успешно удалили Категорию");
        return new ResponseEntity<>(deleteDtoResponse,HttpStatus.OK);
    }
}
