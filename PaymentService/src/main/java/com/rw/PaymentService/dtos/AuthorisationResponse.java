package com.rw.PaymentService.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorisationResponse {
    private String cardSecurityCodeError;
    private String commercialCardIndicator;
    private String date;
    private String financialNetworkCode;
    private String financialNetworkDate;
    private String posData;
    private String posEntryMode;
    private String processingCode;
    private String responseCode;
    private String stan;
    private String time;
    private String transactionIdentifier;
}
