package com.rw.PaymentService.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardInformation {
    private String number;
    private Expiry expiry;
    private String securityCode;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Expiry {
        private int month;
        private int year;
    }
}
