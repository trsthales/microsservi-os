package com.example.ecommerce.payment.usecase;

import com.example.ecommerce.payment.domain.Payment;
import com.example.ecommerce.payment.domain.PaymentStatus;
import com.example.ecommerce.payment.port.PaymentGateway;
import com.example.ecommerce.payment.port.PaymentRepository;
import com.example.ecommerce.payment.web.dto.PaymentRequestDto;
import com.example.ecommerce.payment.web.dto.PaymentResponseDto;
import com.example.ecommerce.payment.web.dto.PaymentResultDto;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProcessPaymentService implements ProcessPaymentUseCase {

    private final PaymentRepository repository;
    private final PaymentGateway gateway;

    public ProcessPaymentService(PaymentRepository repository, PaymentGateway gateway) {
        this.repository = Objects.requireNonNull(repository);
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public PaymentResponseDto process(PaymentRequestDto request) {
        // Basic validation
        if (request.getOrderId() == null || request.getOrderId().isBlank()) {
            throw new IllegalArgumentException("orderId is required");
        }
        if (request.getAmount() == null || request.getAmount().signum() <= 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }

        // Create payment domain object
        Payment payment = new Payment(request.getOrderId(), request.getAmount());
        repository.save(payment);

        // Call external gateway
        PaymentResultDto result = gateway.process(request);
        if (result.isSuccess()) {
            payment.markCompleted();
        } else {
            payment.markFailed();
        }
        repository.save(payment);

        return new PaymentResponseDto(payment.getId(), payment.getStatus().name(), payment.getAmount(), payment.getCreatedAt().toString(), result.getGatewayTransactionId(), result.getErrorMessage());
    }
}

