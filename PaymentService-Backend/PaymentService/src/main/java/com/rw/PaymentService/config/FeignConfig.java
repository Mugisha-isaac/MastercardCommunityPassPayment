package com.rw.PaymentService.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rw.PaymentService.exceptions.BadRequestException;
import com.rw.PaymentService.exceptions.InternalServerErrorException;
import com.rw.PaymentService.exceptions.ResourceNotFoundException;
import com.rw.PaymentService.exceptions.UnAuthorizedException;
import com.rw.PaymentService.util.ApiResponse;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;


@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    private final MessageSource messageSource;

    @Bean
    public RequestInterceptor requestInterceptor(
            @Value("${mastercard.api.username}") String username,
            @Value("${mastercard.api.password}") String password
    ) {
        return requestTemplate -> {
            // Encoding username and password
            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

            // Setting headers
            requestTemplate.header("Authorization", "Basic " + encodedAuth);
            requestTemplate.header("Content-Type", "application/json");
        };
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            String message;
            try {
                message = (String) new ObjectMapper().readValue(response.body().asInputStream().readAllBytes(), ApiResponse.class).getMessage();
            } catch (Exception e) {
                throw new InternalServerErrorException(messageSource.getMessage("responses.internalServerError", null, LocaleContextHolder.getLocale()));
            }

            return switch (response.status()) {
                case 400 -> new BadRequestException(message);
                case 401 -> new UnAuthorizedException(message);
                case 404 -> new ResourceNotFoundException(message);
                case 500 -> new InternalServerErrorException(message);
                default -> new Exception(message);
            };
        };
    }
}
