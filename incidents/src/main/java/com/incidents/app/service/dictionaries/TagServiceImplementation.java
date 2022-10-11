package com.incidents.app.service.dictionaries;


import com.incidents.app.dtos.response.dictionaries.TagDtoRequest;
import com.incidents.app.exception.CustomStatusCode;
import com.incidents.app.exception.ExceptionDescription;
import com.incidents.app.exception.domain.CustomCouldNotCreateException;
import com.incidents.app.exception.domain.CustomNotFoundException;
import com.incidents.app.model.dictionaries.Tag;
import com.incidents.app.repository.dictionaries.TagRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        return tagRepository.getAllByExpiredDateIsNull();
    }

    @Override
    public List<Tag> getAllByListOfIds(List<Long> ids) {
        return ids.stream().map(this::getByIdThrowException).collect(Collectors.toList());
    }

    @Override
    public Tag create(TagDtoRequest dtoRequest) {
        Tag tag;
        try {
            tag = new Tag();
            tag.setTitle(dtoRequest.getTitle());

            return this.save(tag);
        }
        catch (Exception e) {
            log.error(e);
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    @Override
    public Tag update(TagDtoRequest dtoRequest, Long id) {
        Tag tag;
        try {
            tag = this.getByIdThrowException(id);
            tag.setTitle(dtoRequest.getTitle());

            return this.save(tag);
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
            throw new CustomCouldNotCreateException(CustomStatusCode.COULD_NOT_CREATE_RECORD_IN_DB.getCode());
        }
    }

    private Tag save(Tag tag) {
        return tagRepository.save(tag);
    }
}
