package com.academia.bookshop.mappers;

import com.academia.bookshop.exception.ResourceNotFoundException;
import com.academia.bookshop.model.dto.request.AddBookRequestDto;
import com.academia.bookshop.model.dto.response.BookDto;
import com.academia.bookshop.model.entity.Book;
import com.academia.bookshop.repository.AuthorRepository;
import com.academia.bookshop.repository.TagRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

@Mapper(componentModel = "spring", uses = {TagMapper.class, AuthorMapper.class})
public abstract class BookMapper {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public abstract BookDto fromEntity(Book source);

    public abstract List<BookDto> fromEntities(List<Book> sources);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.ZonedDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.ZonedDateTime.now())")
    public abstract Book fromAddRequestDto(AddBookRequestDto source);

    @AfterMapping
    protected void mapAuthor(@MappingTarget Book target, AddBookRequestDto source) {
        if (source == null || source.getAuthorId() == null) {
            return;
        }

        target.setAuthor(
                authorRepository.findById(source.getAuthorId())
                        .orElseThrow(() -> new ResourceNotFoundException("Not found entity 'Author' with id = " + source.getAuthorId()))
        );
    }

    @AfterMapping
    protected void mapTags(@MappingTarget Book target, AddBookRequestDto source) {
        if (source == null || source.getTags() == null) {
            return;
        }

        target.setTags(new HashSet<>(tagRepository.findAllById(source.getTags())));
    }
}
