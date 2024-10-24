package com.rw.PaymentService.service;

import com.rw.PaymentService.clients.MastercardClient;
import com.rw.PaymentService.dtos.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterCardService {
    private final MastercardClient mastercardClient;

    public String authenticate() {
        return mastercardClient.authenticate();
    }

    public String initiatePayment(PaymentRequest paymentRequest) {
        return mastercardClient.initiatePayment(paymentRequest);
    }

    public String checkTransactionStatus(String transactionId) {
        return mastercardClient.checkTransactionStatus(transactionId);
    }

    public String refundPayment(String transactionId) {
        return mastercardClient.refundPayment(transactionId);
    }

    public String getAccountDetails(String accountId) {
        return mastercardClient.getAccountDetails(accountId);
    }
}
