package com.academia.bookshop.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaConstants {
    public static final String BOOK_TOPIC = "book_topic";
    public static final int DEFAULT_NUMBER_OF_PARTITIONS = 3;
    public static final short DEFAULT_REPLICATION_FACTOR = 1;
}
