package com.rw.PaymentService.controller;

import com.rw.PaymentService.dtos.PaymentRequest;
import com.rw.PaymentService.service.MasterCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mastercard")
@RequiredArgsConstructor
public class MasterCardController {
    private final MasterCardService masterCardService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate() {
        return ResponseEntity.ok(masterCardService.authenticate());
    }

    @PostMapping("/payments/initiate")
    public ResponseEntity<String> initiatePayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(masterCardService.initiatePayment(paymentRequest));
    }

    @GetMapping("/payments/status/{transactionId}")
    public ResponseEntity<String> checkTransactionStatus(@PathVariable(name = "transactionId") String transactionId) {
        return ResponseEntity.ok(masterCardService.checkTransactionStatus(transactionId));
    }

    @PostMapping("/payments/refund")
    public ResponseEntity<String> refundPayment(@RequestBody String transactionId) {
        return ResponseEntity.ok(masterCardService.refundPayment(transactionId));
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<String> getAccountDetails(@PathVariable(name = "accountId") String accountId) {
        return ResponseEntity.ok(masterCardService.getAccountDetails(accountId));
    }
}
