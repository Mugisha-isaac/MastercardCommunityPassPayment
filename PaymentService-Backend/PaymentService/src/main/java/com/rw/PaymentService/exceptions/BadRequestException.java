package com.rw.PaymentService.exceptions;

import com.rw.PaymentService.util.ErrorDetail;
import lombok.Getter;


@Getter
public class BadRequestException extends RuntimeException {
    private final ErrorDetail errorDetail;
    private final Object[] args;

    public BadRequestException(String message) {
        super(message);
        this.errorDetail = null;
        this.args = null;
    }

    public BadRequestException(String message, ErrorDetail errorDetail) {
        super(message);
        this.errorDetail = errorDetail;
        this.args = null;
    }

    public BadRequestException(String message, Object[] args) {
        super(message);
        this.args = args;
        this.errorDetail = null;
    }
}