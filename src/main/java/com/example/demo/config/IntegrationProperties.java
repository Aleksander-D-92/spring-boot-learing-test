package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "integration")
public class IntegrationProperties {
    private String starWarsApi;
    private String otherApi;
}
