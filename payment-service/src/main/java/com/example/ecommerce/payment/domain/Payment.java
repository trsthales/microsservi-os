package com.example.ecommerce.payment.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Payment {
    private final String id;
    private final String orderId;
    private final BigDecimal amount;
    private PaymentStatus status;
    private final Instant createdAt;

    public Payment(String orderId, BigDecimal amount) {
        this.id = UUID.randomUUID().toString();
        this.orderId = Objects.requireNonNull(orderId, "orderId");
        this.amount = Objects.requireNonNull(amount, "amount");
        this.status = PaymentStatus.PENDING;
        this.createdAt = Instant.now();
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public BigDecimal getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }

    public void markCompleted() { this.status = PaymentStatus.COMPLETED; }
    public void markFailed() { this.status = PaymentStatus.FAILED; }
    public void markCancelled() { this.status = PaymentStatus.CANCELLED; }
}

