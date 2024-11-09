package com.rw.PaymentService.exceptions;


import com.rw.PaymentService.util.ErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final ErrorDetail errorDetail;
    private final Object[] args;

    public ResourceNotFoundException(String message, ErrorDetail errorDetail) {
        super(message);
        this.errorDetail = errorDetail;
        this.args = null;
    }

    public ResourceNotFoundException(String message, Object[] args) {
        super(message);
        this.args = args;
        this.errorDetail = null;
    }
}
