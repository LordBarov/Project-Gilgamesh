package com.incidents.app.mappers.dictionaries;

import com.incidents.app.dtos.requests.dictionaries.TypeDtoResponse;
import com.incidents.app.model.dictionaries.Type;

public class TypeMapper {

    public static TypeDtoResponse typeToDto(Type type) {
        TypeDtoResponse typeDtoResponse = new TypeDtoResponse();
        typeDtoResponse.setTitle(type.getTitle());
        return typeDtoResponse;
    }
}
