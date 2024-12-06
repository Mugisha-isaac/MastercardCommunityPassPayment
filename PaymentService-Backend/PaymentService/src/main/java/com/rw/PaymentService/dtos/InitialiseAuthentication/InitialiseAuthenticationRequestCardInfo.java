package com.rw.PaymentService.dtos.InitialiseAuthentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InitialiseAuthenticationRequestCardInfo {
    private String number;
}
