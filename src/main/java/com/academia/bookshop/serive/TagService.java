package com.academia.bookshop.serive;

import com.academia.bookshop.model.dto.request.AddTagRequestDto;
import com.academia.bookshop.model.dto.response.TagDto;

import java.util.List;

public interface TagService {
    List<TagDto> findAll();

    List<TagDto> search(String searchValue);

    TagDto add(AddTagRequestDto addTagRequestDto);

    void remove(List<Long> ids);
}
