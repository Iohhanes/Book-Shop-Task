package com.academia.bookshop.model.dto.kafka;

import lombok.Data;

@Data
public class BookKafkaDto {
    private Long id;
    private String title;
    private Double price;
    private String imageUrl;
    private String author;
}
