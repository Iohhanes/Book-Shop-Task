package com.academia.bookshop.mappers;

import com.academia.bookshop.model.dto.request.AddAuthorRequestDto;
import com.academia.bookshop.model.dto.response.AuthorDto;
import com.academia.bookshop.model.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto fromEntity(Author source);

    List<AuthorDto> fromEntities(List<Author> sources);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Author fromAddRequestDto(AddAuthorRequestDto source);
}
