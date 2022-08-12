package com.academia.bookshop.configuration;

import com.academia.bookshop.model.kafka.KafkaEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfiguration {
    private final KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<String, KafkaEvent<Object>> kafkaProducerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
    }

    @Bean
    public KafkaTemplate<String, KafkaEvent<Object>> kafkaTemplate() {
        return new KafkaTemplate<>(kafkaProducerFactory());
    }
}
