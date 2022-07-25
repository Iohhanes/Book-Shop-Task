package com.academia.bookshop.mappers;

import com.academia.bookshop.model.dto.request.AddBookRequestDto;
import com.academia.bookshop.model.dto.response.BookDto;
import com.academia.bookshop.model.entity.Author;
import com.academia.bookshop.model.entity.Book;
import com.academia.bookshop.model.entity.Tag;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {TagMapper.class, AuthorMapper.class})
public abstract class BookMapper {
    public abstract BookDto fromEntity(Book source);

    public abstract List<BookDto> fromEntities(List<Book> sources);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
    @Mapping(target = "updatedAt", expression = "java(new java.util.Date())")
    public abstract Book fromAddRequestDto(AddBookRequestDto source);

    @AfterMapping
    protected void mapAuthor(@MappingTarget Book target, AddBookRequestDto source) {
        if (source == null || source.getAuthorId() == null) {
            return;
        }

        target.setAuthor(
                Author.builder()
                        .id(source.getAuthorId())
                        .build()
        );
    }

    @AfterMapping
    protected void mapTags(@MappingTarget Book target, AddBookRequestDto source) {
        if (source == null || source.getTags() == null) {
            return;
        }

        target.setTags(source.getTags().stream()
                .map(tagId -> Tag.builder()
                        .id(tagId)
                        .build()
                )
                .collect(Collectors.toSet()
                )
        );
    }
}
