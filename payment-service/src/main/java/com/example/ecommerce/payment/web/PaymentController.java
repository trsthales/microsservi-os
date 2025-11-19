package com.example.ecommerce.payment.web;

import com.example.ecommerce.payment.usecase.ProcessPaymentUseCase;
import com.example.ecommerce.payment.web.dto.PaymentRequestDto;
import com.example.ecommerce.payment.web.dto.PaymentResponseDto;
import com.example.ecommerce.payment.port.PaymentRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final ProcessPaymentUseCase processor;
    private final PaymentRepository repository;

    public PaymentController(ProcessPaymentUseCase processor, PaymentRepository repository) {
        this.processor = processor;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> create(@Valid @RequestBody PaymentRequestDto req) {
        PaymentResponseDto resp = processor.process(req);
        return ResponseEntity.created(URI.create("/payments/" + resp.getId())).body(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> get(@PathVariable("id") String id) {
        return repository.findById(id)
                .map(p -> new PaymentResponseDto(p.getId(), p.getStatus().name(), p.getAmount(), p.getCreatedAt().toString(), null, null))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

