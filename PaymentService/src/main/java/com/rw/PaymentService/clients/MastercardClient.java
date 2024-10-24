package com.rw.PaymentService.clients;

import com.rw.PaymentService.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mastercardClient", url = "${mastercard.api.url}", configuration = FeignConfig.class)
public interface MastercardClient {
    @PostMapping("/authenticate")
    String authenticate();

    @PostMapping("/payments/initiate")
    String initiatePayment();
}
