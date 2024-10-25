package com.rw.PaymentService.controller;

import com.rw.PaymentService.dtos.PaymentRequest;
import com.rw.PaymentService.dtos.PaymentResponse;
import com.rw.PaymentService.service.MasterCardService;
import com.rw.PaymentService.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mastercard")
@RequiredArgsConstructor
public class MasterCardController {
    private final MasterCardService masterCardService;

    @PutMapping("/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}")
    public ResponseEntity<ApiResponse<PaymentResponse>> processPayment(
            @PathVariable String merchantId,
            @PathVariable String orderId,
            @PathVariable String transactionId,
            @RequestBody PaymentRequest paymentRequest
    ) {
        PaymentResponse paymentResponse = masterCardService.processPayment(merchantId, orderId, transactionId, paymentRequest);
        return ResponseEntity.ok(ApiResponse.<PaymentResponse>builder().status(201).message("Payment successful").data(paymentResponse).build());
    }
}
