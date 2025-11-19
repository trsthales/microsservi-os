package com.example.ecommerce.payment.adapter.inmemory;

import com.example.ecommerce.payment.port.PaymentGateway;
import com.example.ecommerce.payment.web.dto.PaymentRequestDto;
import com.example.ecommerce.payment.web.dto.PaymentResultDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FakePaymentGateway implements PaymentGateway {
    @Override
    public PaymentResultDto process(PaymentRequestDto request) {
        // Simulate success for positive amounts, fail otherwise
        if (request.getAmount().signum() <= 0) {
            return new PaymentResultDto(false, null, "invalid amount");
        }
        return new PaymentResultDto(true, UUID.randomUUID().toString(), null);
    }
}

