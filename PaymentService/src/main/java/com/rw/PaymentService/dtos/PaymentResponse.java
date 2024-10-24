package com.rw.PaymentService.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private String transactionId;
    private String status;
    private String message;
}
