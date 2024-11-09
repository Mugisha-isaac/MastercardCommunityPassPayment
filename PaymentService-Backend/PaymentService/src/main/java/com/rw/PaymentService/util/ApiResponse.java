package com.rw.PaymentService.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> {
    private T data;
    private Integer status;
    private String message;
    private ErrorDetail error;
    private String result;

    public static <T> ApiResponse<T> successResponse(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setData(data);
        response.setStatus(HttpStatus.OK.value());
        response.setResult("SUCCESS");
        return response;
    }

    public static ApiResponse<?> errorResponse(Integer status, String message, ErrorDetail error) {
        ApiResponse<?> response = new ApiResponse<>();
        response.setStatus(status);
        response.setMessage(message);
        response.setError(error);
        response.setResult("ERROR");
        return response;
    }
}