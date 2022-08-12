package com.academia.bookshop.model.kafka;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KafkaEvent<T> {
    private KafkaEventType eventType;
    private T payload;
}
