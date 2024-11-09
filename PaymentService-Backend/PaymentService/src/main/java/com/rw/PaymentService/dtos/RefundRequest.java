package com.rw.PaymentService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundRequest {
    private String apiOperation;
    private Transaction transaction;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Transaction {
        private double amount;
        private String currency;
    }
}
