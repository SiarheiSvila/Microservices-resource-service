package com.epam.resources.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@AllArgsConstructor
public class AwsConfiguration {

    private final StorageProperties storageProperties;

    @Bean
    public S3Client s3Client() throws URISyntaxException {
        return S3Client.builder()
                .endpointOverride(new URI(storageProperties.getUri()))
                .build();
    }
}
