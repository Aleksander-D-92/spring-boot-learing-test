package com.example.demo;

import com.example.demo.config.IntegrationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties({IntegrationProperties.class})
public class DemoApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(DemoApplication.class, args);
        var env = context.getEnvironment();
        log.info("\n{}\nApplication started with profile: {}\n{}", "*".repeat(70), env.getProperty("spring.profiles.active"), "*".repeat(70));
    }
}
