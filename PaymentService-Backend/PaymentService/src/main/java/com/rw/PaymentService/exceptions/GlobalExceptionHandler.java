package com.rw.PaymentService.exceptions;


import com.rw.PaymentService.util.ErrorDetail;
import com.rw.PaymentService.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableWebMvc
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException ex, Locale locale) {
        String errorMessage = (ex.getArgs() != null)
                ? messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale)
                : ex.getMessage();

        ErrorDetail error = ex.getErrorDetail() != null ?
                ex.getErrorDetail() :
                new ErrorDetail("Resource Not Found", errorMessage, null, null);

        ApiResponse<?> response = ApiResponse.errorResponse(
                HttpStatus.NOT_FOUND.value(),
                errorMessage,
                error
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<?>> handleBadRequestException(BadRequestException ex, Locale locale) {
        log.error("BadRequestException caught: {}", ex.getMessage());

        // Use the MasterCard API error detail if available, otherwise create a new one
        ErrorDetail error = ex.getErrorDetail() != null ?
                ex.getErrorDetail() :
                new ErrorDetail("INVALID_REQUEST", ex.getMessage(), null, null);

        ApiResponse<?> response = ApiResponse.errorResponse(
                HttpStatus.BAD_REQUEST.value(),
                error.getExplanation() != null ? error.getExplanation() : ex.getMessage(),
                error
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, Locale locale) {
        List<ErrorDetail> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorDetail(
                        "VALIDATION_ERROR",
                        error.getDefaultMessage(),
                        error.getField(),
                        "FIELD_VALIDATION"
                ))
                .collect(Collectors.toList());

        String errorMessage = messageSource.getMessage("exceptions.validationError", null, locale);
        ApiResponse<?> response = ApiResponse.errorResponse(
                HttpStatus.BAD_REQUEST.value(),
                errorMessage,
                errors.isEmpty() ? null : errors.get(0)
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse<?>> handleInternalServerErrorException(InternalServerErrorException ex, Locale locale) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);

        ErrorDetail error = ex.getErrorDetail() != null ?
                ex.getErrorDetail() :
                new ErrorDetail("INTERNAL_SERVER_ERROR", errorMessage, null, null);

        ApiResponse<?> response = ApiResponse.errorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                errorMessage,
                error
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiResponse<?>> handleUnAuthorizedException(UnAuthorizedException ex, Locale locale) {
        ErrorDetail error = ex.getErrorDetail() != null ?
                ex.getErrorDetail() :
                new ErrorDetail("UNAUTHORIZED", ex.getMessage(), null, null);

        ApiResponse<?> response = ApiResponse.errorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                error
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGlobalException(Exception ex, Locale locale) {
        log.error("Unexpected error occurred: ", ex);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();

        if (ex instanceof HttpMessageNotReadableException) {
            status = HttpStatus.BAD_REQUEST;
            message = "Malformed JSON request";
        }

        ErrorDetail error = new ErrorDetail(
                ex.getClass().getSimpleName(),
                message,
                null,
                "SYSTEM_ERROR"
        );

        String errorMessage = messageSource.getMessage("exceptions.validation.server", null, locale);
        ApiResponse<?> response = ApiResponse.errorResponse(status.value(), errorMessage, error);
        return new ResponseEntity<>(response, status);
    }
}