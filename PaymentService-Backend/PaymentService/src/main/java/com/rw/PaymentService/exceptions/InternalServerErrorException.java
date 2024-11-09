package com.rw.PaymentService.exceptions;


import com.rw.PaymentService.util.ErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@Getter
public class InternalServerErrorException extends RuntimeException {
    private final ErrorDetail errorDetail;
    private final Object[] args;

    public InternalServerErrorException(String message) {
        super(message);
        this.errorDetail = new ErrorDetail("INTERNAL_SERVER_ERROR", message, null, null);
        this.args = null;
    }

    public InternalServerErrorException(String message, ErrorDetail errorDetail) {
        super(message);
        this.errorDetail = errorDetail;
        this.args = null;
    }

    public InternalServerErrorException(String message, Object[] args) {
        super(message);
        this.args = args;
        this.errorDetail = new ErrorDetail("INTERNAL_SERVER_ERROR", message, null, null);
    }
}
