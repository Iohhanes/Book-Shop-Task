package com.academia.bookshop.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
@Data
public class AppProperties {
    private StorageProperties storage;

    @Data
    public static class StorageProperties{
        private String bucketName;
        private String downloadPublicUrl;
    }
}
