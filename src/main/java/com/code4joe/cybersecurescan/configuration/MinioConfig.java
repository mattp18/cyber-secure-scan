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
                        .credentials("iTBiUPrlrsesUedSBKJ0", "n0q00ExWRNVvBcbVYhAlEnXbAaf7syhaz2KxNME9")
                        .build();
    }
}
