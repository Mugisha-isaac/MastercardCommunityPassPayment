package com.rw.PaymentService.dtos.InitialiseAuthentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class InitiateAuthenticationRequest {
    private String apiOperation;
    private Authentication authentication;
    private Order order;
    private InitiateAuthenticationSourceOfFundsProvided sourceOfFunds;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Authentication {
        private String channel;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Order{
        private String currency;
    }
}
