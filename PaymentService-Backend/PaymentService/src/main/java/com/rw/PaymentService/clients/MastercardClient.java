package com.rw.PaymentService.clients;

import com.rw.PaymentService.config.FeignConfig;
import com.rw.PaymentService.dtos.PaymentRequest;
import com.rw.PaymentService.dtos.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "mastercardClient", url = "${mastercard.api.url}", configuration = FeignConfig.class)
public interface MastercardClient {
  @PutMapping("/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}")
  PaymentResponse processPayment(@PathVariable String merchantId, @PathVariable String orderId, @PathVariable String transactionId, @RequestBody PaymentRequest paymentRequest);
}
