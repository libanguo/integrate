package com.example.integrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootApplication(scanBasePackages = "com.example.integrate")
public class IntegrateApplication {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(IntegrateApplication.class, args);
    }
}
