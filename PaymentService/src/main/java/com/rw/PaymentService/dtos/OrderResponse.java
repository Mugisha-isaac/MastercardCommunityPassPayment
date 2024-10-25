package com.rw.PaymentService.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    @JsonProperty("amount")
    private String amount;  // Changed to String to handle potential decimal values
    private String authenticationStatus;  // Changed from enum to String
    private Chargeback chargeback;
    private String creationTime;
    private String currency;
    private String id;  // Changed from UUID to String
    private String lastUpdatedTime;
    private String merchantAmount;
    private String merchantCategoryCode;
    private String merchantCurrency;
    private String status;
    private String totalAuthorizedAmount;
    private String totalCapturedAmount;
    private String totalDisbursedAmount;
    private String totalRefundedAmount;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Chargeback {
        private String amount;
        private String currency;
    }
}
