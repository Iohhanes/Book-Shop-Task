package com.academia.bookshop.service;

import com.academia.bookshop.model.entity.Book;

public interface KafkaProducerService {
    void sendNewBookCreationEvent(Book newBook);
}
