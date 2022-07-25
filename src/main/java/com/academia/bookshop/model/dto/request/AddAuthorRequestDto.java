package com.academia.bookshop.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAuthorRequestDto {
    private String firstName;
    private String lastName;
}
