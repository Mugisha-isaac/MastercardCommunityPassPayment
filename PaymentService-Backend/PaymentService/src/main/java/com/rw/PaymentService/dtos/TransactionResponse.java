package com.rw.PaymentService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Acquirer acquirer;
    private double amount;
    private String authenticationStatus;
    private String authorizationCode;
    private String currency;
    private UUID id;
    private String receipt;
    private String source;
    private String stan;
    private String terminal;
    private String type;


    @Data
    private static class Acquirer {
        private Long batch;
        private String date;
        private String id;
        private String merchantId;
        private String settlementDate;
        private String timeZone;
        private String transactionId;
    }
}
