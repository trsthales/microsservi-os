package com.example.ecommerce.payment.port;

import com.example.ecommerce.payment.web.dto.PaymentRequestDto;
import com.example.ecommerce.payment.web.dto.PaymentResultDto;

public interface PaymentGateway {
    PaymentResultDto process(PaymentRequestDto request);
}

