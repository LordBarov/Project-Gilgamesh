package com.incidents.app.service.dictionaries;

import com.incidents.app.dtos.response.dictionaries.TypeDtoRequest;
import com.incidents.app.model.dictionaries.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService {

    Optional<Type> getById(Long id);

    Type getByIdThrowException(Long id);

    List<Type> getAll();

    List<Type> getAllByListOfIds(List<Long> ids);

    Type create(TypeDtoRequest typeDtoRequest);

    Type update(TypeDtoRequest typeDtoRequest, Long id);

    void delete(Long id);
}
