package com.rw.PaymentService.controller;

import com.rw.PaymentService.dtos.InitialiseAuthentication.InitiateAuthenticationRequest;
import com.rw.PaymentService.dtos.PaymentRequest;
import com.rw.PaymentService.dtos.PaymentResponse;
import com.rw.PaymentService.dtos.RefundRequest;
import com.rw.PaymentService.exceptions.BadRequestException;
import com.rw.PaymentService.service.MasterCardService;
import com.rw.PaymentService.util.ApiResponse;
import com.rw.PaymentService.util.ErrorDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mastercard")
@RequiredArgsConstructor
public class MasterCardController {
    private final MasterCardService masterCardService;
    private final Logger log = LoggerFactory.getLogger(MasterCardController.class);


    @PutMapping("/pay/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}")
    public ResponseEntity<ApiResponse<PaymentResponse>> processPayment(
            @PathVariable String merchantId,
            @PathVariable String orderId,
            @PathVariable String transactionId,
            @Valid @RequestBody PaymentRequest paymentRequest
    ) {
        log.info("Processing payment for merchant: {}, order: {}, transaction: {}",
                merchantId, orderId, transactionId);

        PaymentResponse result = masterCardService.processPayment(merchantId, orderId, transactionId, paymentRequest);
        return ResponseEntity.ok(ApiResponse.successResponse(result));
    }

    @PutMapping("/refund/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}")
    public ResponseEntity<Object> processRefund(
            @PathVariable String merchantId,
            @PathVariable String orderId,
            @PathVariable String transactionId,
            @RequestBody RefundRequest refundRequest
    ) {

        return ResponseEntity.ok(ApiResponse.successResponse(masterCardService.processRefund(merchantId, orderId, transactionId, refundRequest)));
    }

    @PutMapping("/initiateAuthentication/merchant/{merchantId}/order/{orderId}/transaction/{transactionId}")
    public ResponseEntity<Object> initiateAuthentication(
            @PathVariable String merchantId,
            @PathVariable String orderId,
            @PathVariable String transactionId,
            @RequestBody InitiateAuthenticationRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.successResponse(masterCardService.initiateAuthentication(merchantId, orderId, transactionId, request)));
    }
}
