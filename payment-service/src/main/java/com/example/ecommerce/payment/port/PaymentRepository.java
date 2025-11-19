package com.example.ecommerce.payment.port;

import com.example.ecommerce.payment.domain.Payment;

import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(String id);
}

