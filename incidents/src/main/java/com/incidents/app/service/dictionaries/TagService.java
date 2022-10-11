package com.incidents.app.service.dictionaries;

import com.incidents.app.dtos.response.dictionaries.TagDtoRequest;
import com.incidents.app.model.dictionaries.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    Optional<Tag> getById(Long id);

    Tag getByIdThrowException(Long id);

    List<Tag> getAll();

    List<Tag> getAllByListOfIds(List<Long> ids);

    Tag create(TagDtoRequest dtoRequest);

    Tag update(TagDtoRequest dtoRequest, Long id);

    void delete(Long id);
}
