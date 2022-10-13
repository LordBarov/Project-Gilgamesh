package com.incidents.app.mappers.dictionaries;

import com.incidents.app.dtos.response.dictionaries.TagDtoResponse;
import com.incidents.app.model.dictionaries.Tag;

public class TagMapper {

    public static TagDtoResponse tagToDto(Tag tag) {
        TagDtoResponse dtoResponse = new TagDtoResponse();
        dtoResponse.setTitle(tag.getTitle());
        return dtoResponse;
    }
}
