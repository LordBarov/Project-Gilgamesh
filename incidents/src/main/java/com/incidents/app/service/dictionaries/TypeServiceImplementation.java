package com.incidents.app.service.dictionaries;

import com.incidents.app.dtos.requests.dictionaries.TypeDtoRequest;
import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomCouldNotCreateException;
import com.incidents.app.exception.domain.CustomCouldNotDeleteException;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.dictionaries.Type;
import com.incidents.app.repository.dictionaries.TypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class TypeServiceImplementation implements TypeService{

    private final TypeRepository typeRepository;

    @Override
    public Optional<Type> getById(Long id) {
        return typeRepository.getByIdAndExpiredDateIsNull(id);
    }

    @Override
    public Type getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "type", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<Type> getAll() {
        return typeRepository.getAllByExpiredDateIsNull();
    }

    @Override
    public List<Type> getAllByListOfIds(List<Long> ids) {
        return ids.stream().map(this::getByIdThrowException).collect(Collectors.toList());
    }

    @Override
    public Type create(TypeDtoRequest typeDtoRequest) {
        Type type;
        try {
            type = new Type();
            type.setTitle(typeDtoRequest.getTitle());

            return this.save(type);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public Type update(TypeDtoRequest typeDtoRequest, Long id) {
        Type type;
        try {
            type = this.getByIdThrowException(id);
            type.setTitle(typeDtoRequest.getTitle());

            return this.save(type);
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

    private Type save(Type type) {
        return typeRepository.save(type);
    }
}
