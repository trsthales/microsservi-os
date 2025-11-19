package com.example.ecommerce.cart.web;

import com.example.ecommerce.cart.usecase.AddItemToCartUseCase;
import com.example.ecommerce.cart.web.dto.AddItemRequest;
import com.example.ecommerce.cart.web.dto.CartResponse;
import com.example.ecommerce.cart.port.out.CartRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final AddItemToCartUseCase addItemUseCase;
    private final CartRepository repository;

    public CartController(AddItemToCartUseCase addItemUseCase, CartRepository repository) {
        this.addItemUseCase = addItemUseCase;
        this.repository = repository;
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<Void> addItem(@PathVariable("userId") UUID userId, @Valid @RequestBody AddItemRequest req) {
        addItemUseCase.add(userId, req.getProductId(), req.getQuantity(), req.getPriceSnapshot());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable("userId") UUID userId) {
        return repository.findByUserId(userId)
                .map(cart -> ResponseEntity.ok(CartResponse.fromDomain(cart)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
