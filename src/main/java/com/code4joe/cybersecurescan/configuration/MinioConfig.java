package com.code4joe.cybersecurescan.configuration;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient() {
               return MinioClient.builder()
                        .endpoint("http://localhost:9000/")
                        .credentials("koo3ItqKVjL0QlHw8zkD", "PiiE7uK2eUw6neZcHOA8XvcQqrWbksMa3Bxdtj09")
                        .build();
    }
}
