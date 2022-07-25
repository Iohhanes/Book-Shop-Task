package com.academia.bookshop.mappers;

import com.academia.bookshop.model.dto.request.AddTagRequestDto;
import com.academia.bookshop.model.dto.response.TagDto;
import com.academia.bookshop.model.entity.Tag;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDto fromEntity(Tag source);

    Set<TagDto> fromEntities(Set<TagDto> sources);

    Tag fromAddRequestDto(AddTagRequestDto source);
}
