package com.academia.bookshop.model.dto.request;

import com.academia.bookshop.model.dto.response.AuthorDto;
import com.academia.bookshop.model.dto.response.TagDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequestDto {
    private Long id;
    private String title;
    private Double price;
    private Long authorId;
    private Set<Long> tags;
}
