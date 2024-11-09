package com.rw.PaymentService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rw.PaymentService.clients.MastercardClient;
import com.rw.PaymentService.dtos.PaymentRequest;
import com.rw.PaymentService.dtos.PaymentResponse;
import com.rw.PaymentService.dtos.RefundRequest;
import com.rw.PaymentService.exceptions.BadRequestException;
import com.rw.PaymentService.exceptions.InternalServerErrorException;
import com.rw.PaymentService.exceptions.ResourceNotFoundException;
import com.rw.PaymentService.exceptions.UnAuthorizedException;
import com.rw.PaymentService.util.ApiResponse;
import com.rw.PaymentService.util.CustomErrorResponse;
import com.rw.PaymentService.util.ErrorDetail;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterCardService {
    private final MastercardClient mastercardClient;
    private final Logger logger = LoggerFactory.getLogger(MasterCardService.class);


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
        ApiResponse<PaymentResponse> response = mastercardClient.processRefund(merchantId, orderId, transactionId, refundRequest);
        if (response.getError() != null) {
            throw new InternalServerErrorException(
                    response.getError().getExplanation(),
                    response.getError()
            );
        }
        return response.getData();
    }
}
