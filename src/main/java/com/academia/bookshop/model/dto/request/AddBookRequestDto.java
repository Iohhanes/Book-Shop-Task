package com.academia.bookshop.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequestDto {
    @Size(min = 1, max = 255)
    private String title;
    private Double price;
    private Long authorId;
    private Set<Long> tags;
}
