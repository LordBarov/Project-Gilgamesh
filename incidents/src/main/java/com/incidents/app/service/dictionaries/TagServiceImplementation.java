package com.incidents.app.service.dictionaries;


import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.dictionaries.Tag;
import com.incidents.app.repository.dictionaries.TagRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class TagServiceImplementation implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Optional<Tag> getById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Tag getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> {
            log.error(String.format(ExceptionDescription.CUSTOM_NOT_FOUND_EXCEPTION, "tag", "id", id));
            throw new CustomNotFoundException(CustomStatusCode.NOT_FOUND_IN_DB.getCode());
        });
    }

    @Override
    public List<Tag> getAll() {
        return null;
    }

    @Override
    public List<Tag> getAllByListOfIds(List<Long> ids) {
        return ids.stream().map(this::getByIdThrowException).collect(Collectors.toList());
    }

    @Override
    public Tag create() {
        return null;
    }

    @Override
    public Tag update() {
        return null;
    }

    @Override
    public void delete() {

    }
}
