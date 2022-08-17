package com.academia.bookshop.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Integer id;
    private String title;
    private Double price;
    private String imageUrl;
    private Date createdAt;
    private Date updatedAt;
    private AuthorDto author;
    private Set<TagDto> tags;
}
