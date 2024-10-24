package com.rw.PaymentService.clients;

import com.rw.PaymentService.config.FeignConfig;
import com.rw.PaymentService.dtos.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mastercardClient", url = "${mastercard.api.url}", configuration = FeignConfig.class)
public interface MastercardClient {
    @PostMapping("/authenticate")
    String authenticate();

    @PostMapping("/payments/initiate")
    String initiatePayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/payments/status/{transactionId}")
    String checkTransactionStatus(@PathVariable(name = "transactionId") String transactionId);

    @PostMapping("/payments/refund")
    String refundPayment(@RequestBody String transactionId);

    @GetMapping("/accounts/{accountId}")
    String getAccountDetails(@PathVariable(name = "accountId") String accountId);
}
