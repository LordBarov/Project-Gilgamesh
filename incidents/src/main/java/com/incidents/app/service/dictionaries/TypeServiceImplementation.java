package com.incidents.app.service.dictionaries;

import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.dictionaries.Type;
import com.incidents.app.repository.dictionaries.TypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
        return typeRepository.findById(id);
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
        return null;
    }

    @Override
    public List<Type> getAllByListOfIds(List<Long> ids) {
        return ids.stream().map(this::getByIdThrowException).collect(Collectors.toList());
    }

    @Override
    public Type create() {
        return null;
    }

    @Override
    public Type update() {
        return null;
    }

    @Override
    public void delete() {

    }
}
