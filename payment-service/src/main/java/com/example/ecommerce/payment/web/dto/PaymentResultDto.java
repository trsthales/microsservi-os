package com.example.ecommerce.payment.web.dto;

public class PaymentResultDto {
    private boolean success;
    private String gatewayTransactionId;
    private String errorMessage;

    public PaymentResultDto() {}

    public PaymentResultDto(boolean success, String gatewayTransactionId, String errorMessage) {
        this.success = success;
        this.gatewayTransactionId = gatewayTransactionId;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() { return success; }
    public String getGatewayTransactionId() { return gatewayTransactionId; }
    public String getErrorMessage() { return errorMessage; }
}

