package com.rw.PaymentService.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRiskResponse {
    private Response response;
    @Data
    private static class Response {
        private String gatewayCode;
        private String provider;
        private ResponseReview review;
        private List<TransactionRiskRule> rule;
        private String totalScore;
    }

    @Data
    private static class ResponseReview {
        private String decision;
    }
}
