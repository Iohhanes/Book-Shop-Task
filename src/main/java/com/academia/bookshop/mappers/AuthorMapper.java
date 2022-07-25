package com.academia.bookshop.mappers;

import com.academia.bookshop.model.dto.request.AddAuthorRequestDto;
import com.academia.bookshop.model.dto.response.AuthorDto;
import com.academia.bookshop.model.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto fromEntity(Author source);

    Author fromAddRequestDto(AddAuthorRequestDto source);
}
