package com.academia.bookshop.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import static com.academia.bookshop.constants.KafkaConstants.*;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfiguration {
    private final KafkaProperties kafkaProperties;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(kafkaProperties.buildAdminProperties());
    }

    @Bean
    public NewTopic bookTopic() {
        return new NewTopic(BOOK_TOPIC, DEFAULT_NUMBER_OF_PARTITIONS, DEFAULT_REPLICATION_FACTOR);
    }
}
