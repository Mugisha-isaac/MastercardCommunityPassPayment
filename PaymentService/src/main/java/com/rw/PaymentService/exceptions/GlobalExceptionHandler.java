package com.rw.PaymentService.exceptions;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.rw.PaymentService.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableWebMvc
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, Locale locale) {
       String errorMessage = (ex.getArgs() != null) ? messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale) : ex.getMessage();
       return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(),errorMessage,null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> badRequestException(BadRequestException ex, Locale locale) {
        String errorMessage = (ex.getArgs() != null) ? messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale) : ex.getMessage();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),errorMessage,null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> unAuthorizedException(UnAuthorizedException ex, Locale locale) {
        String errorMessage = (ex.getArgs() != null) ? messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale) : ex.getMessage();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(),errorMessage,null), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<?> duplicateRecordException(DuplicateRecordException ex, Locale locale) {
        String errorMessage = (ex.getArgs() != null) ? messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale) : ex.getMessage();
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),errorMessage,null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, Locale locale) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String errorMessage = messageSource.getMessage("exceptions.validationError", null, locale);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),errorMessage,errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, Locale locale) throws JsonProcessingException {
        String message = ex.getMessage();
        Object error = ex.getMessage();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(ex.getClass().getSimpleName().equals("InternalAuthenticationServiceException")) {
            status = HttpStatus.UNAUTHORIZED;
        }

        if(ex.getClass().getSimpleName().equals("HttpMessageNotReadableException")) {
            status = HttpStatus.BAD_REQUEST;
            message = "Malformed JSON request";
        }

        String errorMessage = messageSource.getMessage("exceptions.validation.server", null, locale);
        return new ResponseEntity<>(new ApiResponse<>(status.value(),errorMessage,error), status);
    }
}
