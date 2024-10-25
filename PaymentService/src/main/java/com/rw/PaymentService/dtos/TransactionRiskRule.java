package com.rw.PaymentService.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionRiskRule {
    private String data;
    private String name;
    private String recommendation;
    private String type;
}
