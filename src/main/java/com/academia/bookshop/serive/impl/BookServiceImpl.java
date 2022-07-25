package com.academia.bookshop.serive.impl;

import com.academia.bookshop.mappers.BookMapper;
import com.academia.bookshop.model.dto.request.AddBookRequestDto;
import com.academia.bookshop.model.dto.response.BookDto;
import com.academia.bookshop.model.entity.Book;
import com.academia.bookshop.repository.BookRepository;
import com.academia.bookshop.serive.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.fromEntities(books);
    }

    @Override
    public List<BookDto> search(String search) {
        if (StringUtils.hasLength(search)) {
            List<Book> books = bookRepository.search(search.toLowerCase());
            return bookMapper.fromEntities(books);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public BookDto add(AddBookRequestDto addBookRequestDto) {
        Book book = bookMapper.fromAddRequestDto(addBookRequestDto);
        book = bookRepository.save(book);
        return bookMapper.fromEntity(book);
    }
}
