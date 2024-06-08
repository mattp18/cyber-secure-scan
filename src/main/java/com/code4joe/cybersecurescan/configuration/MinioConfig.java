package com.code4joe.cybersecurescan.configuration;

import com.code4joe.cybersecurescan.backend.service.impl.PdfServiceImpl;
import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${minio.access.key}")
    private String accessKey;

    @Value("${minio.secret.key}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
               return MinioClient.builder()
                        .endpoint("http://localhost:9000/")
                        .credentials(accessKey, secretKey)
                        .build();
    }
}
