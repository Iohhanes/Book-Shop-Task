package com.academia.bookshop.mappers;

import com.academia.bookshop.model.dto.request.AddTagRequestDto;
import com.academia.bookshop.model.dto.response.TagDto;
import com.academia.bookshop.model.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto fromEntity(Tag source);

    Set<TagDto> fromEntities(Set<Tag> sources);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    Tag fromAddRequestDto(AddTagRequestDto source);
}
