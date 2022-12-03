package com.wsf.testimport;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportConfig {

    @Bean
    public TestImport testImport() {
        return new TestImport();
    }
}
