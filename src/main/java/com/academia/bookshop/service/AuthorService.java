package com.academia.bookshop.service;

import com.academia.bookshop.model.dto.request.AddAuthorRequestDto;
import com.academia.bookshop.model.dto.response.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();

    List<AuthorDto> search(String searchValue);

    AuthorDto add(AddAuthorRequestDto addAuthorRequestDto);

    void remove(List<Integer> ids);
}
