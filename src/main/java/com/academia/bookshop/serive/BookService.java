package com.academia.bookshop.serive;

import com.academia.bookshop.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
}
