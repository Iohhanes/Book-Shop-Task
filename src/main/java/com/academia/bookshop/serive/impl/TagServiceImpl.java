package com.academia.bookshop.serive.impl;

import com.academia.bookshop.mappers.TagMapper;
import com.academia.bookshop.model.dto.request.AddTagRequestDto;
import com.academia.bookshop.model.dto.response.TagDto;
import com.academia.bookshop.model.entity.Tag;
import com.academia.bookshop.repository.TagRepository;
import com.academia.bookshop.serive.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Transactional(readOnly = true)
    @Override
    public List<TagDto> findAll() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(tagMapper::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<TagDto> search(String searchValue) {
        if (StringUtils.hasLength(searchValue)) {
            List<Tag> tags = tagRepository.search(searchValue.toLowerCase());
            return tags.stream().map(tagMapper::fromEntity).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    @Override
    public TagDto add(AddTagRequestDto addTagRequestDto) {
        Tag tag = tagMapper.fromAddRequestDto(addTagRequestDto);
        tag = tagRepository.save(tag);
        return tagMapper.fromEntity(tag);
    }

    @Transactional
    @Override
    public void remove(List<Long> ids) {
        tagRepository.deleteAllByIdInBatch(ids);
    }
}
