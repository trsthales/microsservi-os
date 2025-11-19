package com.example.ecommerce.payment.usecase;

import com.example.ecommerce.payment.web.dto.PaymentRequestDto;
import com.example.ecommerce.payment.web.dto.PaymentResponseDto;

public interface ProcessPaymentUseCase {
    PaymentResponseDto process(PaymentRequestDto request);
}

