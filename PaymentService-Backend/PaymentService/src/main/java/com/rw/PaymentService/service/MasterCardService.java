package com.rw.PaymentService.service;

import com.rw.PaymentService.clients.MastercardClient;
import com.rw.PaymentService.dtos.PaymentRequest;
import com.rw.PaymentService.dtos.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterCardService {
    private final MastercardClient mastercardClient;

    public PaymentResponse processPayment(String merchantId, String orderId, String transactionId, PaymentRequest paymentRequest) {
        return mastercardClient.processPayment(merchantId, orderId, transactionId, paymentRequest);
    }
}
