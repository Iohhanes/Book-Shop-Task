package com.academia.bookshop.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTagRequestDto {
    @Size(min = 1, max = 100)
    String title;
}
