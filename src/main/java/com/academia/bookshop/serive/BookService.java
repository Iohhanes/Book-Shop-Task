package com.academia.bookshop.serive;

import com.academia.bookshop.model.dto.request.AddBookRequestDto;
import com.academia.bookshop.model.dto.response.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> findAll();

    List<BookDto> search(String search);

    BookDto add(AddBookRequestDto addBookRequestDto);
}
