package com.academia.bookshop.service.impl;

import com.academia.bookshop.mappers.kafka.BookKafkaMapper;
import com.academia.bookshop.model.dto.kafka.BookKafkaDto;
import com.academia.bookshop.model.entity.Book;
import com.academia.bookshop.model.kafka.KafkaEvent;
import com.academia.bookshop.model.kafka.KafkaEventType;
import com.academia.bookshop.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.academia.bookshop.constants.KafkaConstants.BOOK_TOPIC;

@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaTemplate<String, KafkaEvent<Object>> kafkaTemplate;
    private final BookKafkaMapper bookKafkaMapper;

    @Override
    public void sendNewBookCreationEvent(Book newBook) {
        BookKafkaDto payload = bookKafkaMapper.from(newBook);

        KafkaEvent<Object> kafkaEvent = KafkaEvent.builder()
                .eventType(KafkaEventType.CREATE_NEW_BOOK)
                .payload(payload)
                .build();

        kafkaTemplate.send(BOOK_TOPIC, kafkaEvent);
    }
}
