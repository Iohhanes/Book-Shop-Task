package com.academia.bookshop.mappers.kafka;

import com.academia.bookshop.model.dto.kafka.BookKafkaDto;
import com.academia.bookshop.model.entity.Author;
import com.academia.bookshop.model.entity.Book;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class BookKafkaMapper {

    @Mapping(target = "author", ignore = true)
    public abstract BookKafkaDto from(Book source);

    @AfterMapping
    protected void mapAuthor(@MappingTarget BookKafkaDto target, Book source) {
        if (source == null || source.getAuthor() == null) {
            return;
        }

        Author author = source.getAuthor();
        target.setAuthor(author.getFirstName() + " " + author.getLastName());
    }
}
