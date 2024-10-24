package com.rw.PaymentService.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    private String amount;
    private String currency;
    private String merchantId;
    private String cardNumber;
}
