package com.rw.PaymentService.service;


import com.rw.PaymentService.clients.MastercardClient;
import com.rw.PaymentService.dtos.InitialiseAuthentication.InitiateAuthenticationRequest;
import com.rw.PaymentService.dtos.PaymentRequest;
import com.rw.PaymentService.dtos.PaymentResponse;
import com.rw.PaymentService.dtos.RefundRequest;
import com.rw.PaymentService.exceptions.InternalServerErrorException;
import com.rw.PaymentService.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterCardService {
    private final MastercardClient mastercardClient;


    public PaymentResponse processPayment(String merchantId, String orderId, String transactionId, PaymentRequest paymentRequest) {

        ApiResponse<PaymentResponse> response = mastercardClient.processPayment(merchantId, orderId, transactionId, paymentRequest);

        if (response.getError() != null) {
            throw new InternalServerErrorException(
                    response.getError().getExplanation(),
                    response.getError()
            );
        }


        return response.getData();
    }

    public PaymentResponse processRefund(String merchantId, String orderId, String transactionId, RefundRequest refundRequest) {
        return null;
    }

    public Object initiateAuthentication(String merchantId, String orderId, String transactionId, InitiateAuthenticationRequest request) {
        return null ;
    }
}
