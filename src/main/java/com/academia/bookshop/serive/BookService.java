package com.academia.bookshop.serive;

import com.academia.bookshop.model.dto.request.AddBookRequestDto;
import com.academia.bookshop.model.dto.response.BookDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<BookDto> findAll();

    List<BookDto> search(String searchValue);

    BookDto add(AddBookRequestDto addBookRequestDto, MultipartFile image) throws IOException;

    void remove(List<Long> ids);
}
