package com.example.ecommerce.payment.usecase;

import com.example.ecommerce.payment.adapter.inmemory.InMemoryPaymentRepository;
import com.example.ecommerce.payment.adapter.inmemory.FakePaymentGateway;
import com.example.ecommerce.payment.web.dto.PaymentRequestDto;
import com.example.ecommerce.payment.web.dto.PaymentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProcessPaymentServiceTest {

    private InMemoryPaymentRepository repo;
    private FakePaymentGateway gateway;
    private ProcessPaymentService service;

    @BeforeEach
    void setup() {
        repo = new InMemoryPaymentRepository();
        gateway = new FakePaymentGateway();
        service = new ProcessPaymentService(repo, gateway);
    }

    @Test
    void process_success() {
        PaymentRequestDto req = new PaymentRequestDto();
        req.setOrderId("order-1");
        req.setAmount(new BigDecimal("10.00"));

        PaymentResponseDto resp = service.process(req);
        assertNotNull(resp.getId());
        assertEquals("COMPLETED", resp.getStatus());
        assertEquals(new BigDecimal("10.00"), resp.getAmount());
    }

    @Test
    void process_invalidAmount_throws() {
        PaymentRequestDto req = new PaymentRequestDto();
        req.setOrderId("order-1");
        req.setAmount(new BigDecimal("0"));

        assertThrows(IllegalArgumentException.class, () -> service.process(req));
    }
}

