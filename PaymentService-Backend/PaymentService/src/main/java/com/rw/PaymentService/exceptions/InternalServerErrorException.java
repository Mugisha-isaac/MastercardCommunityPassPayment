package com.rw.PaymentService.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
@AllArgsConstructor
public class InternalServerErrorException extends RuntimeException {
    private String message = "exceptions.internalServerError";
    private Object[] args;

    public InternalServerErrorException(Object... args) {
        super("Message");
        this.args = args;
    }
}
