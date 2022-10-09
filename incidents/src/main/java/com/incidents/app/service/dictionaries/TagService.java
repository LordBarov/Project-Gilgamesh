package com.incidents.app.service.dictionaries;

import com.incidents.app.model.dictionaries.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    Optional<Tag> getById(Long id);

    Tag getByIdThrowException(Long id);

    List<Tag> getAll();

    List<Tag> getAllByListOfIds(List<Long> ids);

    Tag create();

    Tag update();

    void delete();
}
