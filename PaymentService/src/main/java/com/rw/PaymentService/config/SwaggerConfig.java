package com.rw.PaymentService.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
       info = @Info(
                title = "Payment Service API",
                version = "1.0",
                description = "This is the Payment Service API documentation"
        )
)
@Configuration
public class SwaggerConfig {
}
