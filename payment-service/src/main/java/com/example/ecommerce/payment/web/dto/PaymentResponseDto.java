package com.example.ecommerce.payment.web.dto;

import java.math.BigDecimal;

public class PaymentResponseDto {
    private String id;
    private String status;
    private BigDecimal amount;
    private String createdAt;
    private String gatewayTransactionId;
    private String errorMessage;

    public PaymentResponseDto() {}

    public PaymentResponseDto(String id, String status, BigDecimal amount, String createdAt, String gatewayTransactionId, String errorMessage) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.createdAt = createdAt;
        this.gatewayTransactionId = gatewayTransactionId;
        this.errorMessage = errorMessage;
    }

    public String getId() { return id; }
    public String getStatus() { return status; }
    public BigDecimal getAmount() { return amount; }
    public String getCreatedAt() { return createdAt; }
    public String getGatewayTransactionId() { return gatewayTransactionId; }
    public String getErrorMessage() { return errorMessage; }
}

