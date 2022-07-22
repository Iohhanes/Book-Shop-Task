package com.academia.bookshop.serive.impl;

import com.academia.bookshop.model.entity.Book;
import com.academia.bookshop.repository.BookRepository;
import com.academia.bookshop.serive.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
