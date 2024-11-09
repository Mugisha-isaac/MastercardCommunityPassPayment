package com.rw.PaymentService.exceptions;


import com.rw.PaymentService.util.ErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class UnAuthorizedException extends RuntimeException {
    private final ErrorDetail errorDetail;
    private final Object[] args;

    public UnAuthorizedException(String message) {
        super(message);
        this.errorDetail = new ErrorDetail("UNAUTHORIZED", message, null, null);
        this.args = null;
    }

    public UnAuthorizedException(String message, ErrorDetail errorDetail) {
        super(message);
        this.errorDetail = errorDetail;
        this.args = null;
    }

    public UnAuthorizedException(String message, Object[] args) {
        super(message);
        this.args = args;
        this.errorDetail = new ErrorDetail("UNAUTHORIZED", message, null, null);
    }
}