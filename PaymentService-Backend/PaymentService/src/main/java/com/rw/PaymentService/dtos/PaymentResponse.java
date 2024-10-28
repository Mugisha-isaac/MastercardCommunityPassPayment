package com.rw.PaymentService.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private AuthorisationResponse authorizationResponse;
    private String gatewayEntryPoint;
    private String merchant;
    private OrderResponse order;
    private Response response;
    private String result;
    private TransactionRiskResponse risk;
    private SourceOfFunds sourceOfFunds;
    private String timeOfLastUpdate;
    private String timeOfRecord;
    private String version;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String acquirerCode;
        private String acquirerMessage;
        private CardSecurityCode cardSecurityCode;
        private String gatewayCode;
        private String gatewayRecommendation;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardSecurityCode {
        private String acquirerCode;
        private String gatewayCode;
    }
}
