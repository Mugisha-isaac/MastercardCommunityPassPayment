package com.rw.PaymentService.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private String message = "exceptions.notfound";
    private Object[] args;

    public ResourceNotFoundException(Object... args) {
        super("Message");
        this.args = args;
    }
}
