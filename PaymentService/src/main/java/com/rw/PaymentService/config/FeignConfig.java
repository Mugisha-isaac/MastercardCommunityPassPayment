package com.rw.PaymentService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

@Configuration
public class FeignConfig {
    @Bean
    public BasicAuthenticationInterceptor basicAuthRequestInterceptor(
            @Value("${mastercard.api.username}") String username,
            @Value("${mastercard.api.password}") String password
    ) {
        return new BasicAuthenticationInterceptor("user", "password");
    }
}
