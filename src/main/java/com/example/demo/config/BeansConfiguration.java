package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Configuration
public class BeansConfiguration {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        ClientHttpRequestInterceptor loggingInterceptor = new RestTemplateLoggingInterceptor();
        return builder
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .additionalInterceptors(loggingInterceptor)
                .setConnectTimeout(Duration.ofSeconds(2))
                .build();
    }

    @Slf4j
    private static class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            log.info("Sending {} request to {}", request.getMethod(), request.getURI());
            if (body.length > 0) {
                log.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
            }
            ClientHttpResponse response = execution.execute(request, body);
            log.info("Received response with status code {}", response.getStatusCode());
            byte[] responseBody = StreamUtils.copyToByteArray(response.getBody());
            if (responseBody.length > 0) {
                log.info("Response body: {}", new String(responseBody, StandardCharsets.UTF_8));
            }
            return response;
        }
    }
}
