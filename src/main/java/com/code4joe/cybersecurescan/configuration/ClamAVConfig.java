package com.code4joe.cybersecurescan.configuration;

import com.code4joe.cybersecurescan.shared.constant.MalwareScanConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.capybara.clamav.ClamavClient;

@Configuration
public class ClamAVConfig {

    @Bean
    public ClamavClient clamavClient() {
        return new ClamavClient("172.24.0.2", 3310);
    }
}
