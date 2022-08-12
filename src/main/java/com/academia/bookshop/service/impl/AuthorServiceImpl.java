package com.academia.bookshop.service.impl;

import com.academia.bookshop.mappers.AuthorMapper;
import com.academia.bookshop.model.dto.request.AddAuthorRequestDto;
import com.academia.bookshop.model.dto.response.AuthorDto;
import com.academia.bookshop.model.entity.Author;
import com.academia.bookshop.repository.AuthorRepository;
import com.academia.bookshop.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Transactional(readOnly = true)
    @Override
    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.fromEntities(authors);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AuthorDto> search(String searchValue) {
        if (StringUtils.hasLength(searchValue)) {
            List<Author> authors = authorRepository.search(searchValue.toLowerCase());
            return authorMapper.fromEntities(authors);
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    @Override
    public AuthorDto add(AddAuthorRequestDto addAuthorRequestDto)  {
        Author author = authorMapper.fromAddRequestDto(addAuthorRequestDto);
        author = authorRepository.save(author);
        return authorMapper.fromEntity(author);
    }

    @Transactional
    @Override
    public void remove(List<Long> ids) {
        authorRepository.deleteAllByIdInBatch(ids);
    }
}
