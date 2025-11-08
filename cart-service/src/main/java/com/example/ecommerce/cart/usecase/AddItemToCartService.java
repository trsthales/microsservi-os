package com.example.ecommerce.cart.usecase;

import com.example.ecommerce.cart.domain.Cart;
import com.example.ecommerce.cart.port.out.CartRepository;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Implementação INCOMPLETA para manter testes vermelhos inicialmente.
 */
public class AddItemToCartService implements AddItemToCartUseCase {

    private final CartRepository repository;

    public AddItemToCartService(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(UUID userId, UUID productId, int quantity, BigDecimal priceSnapshot) {
        throw new UnsupportedOperationException("Implementar lógica de adicionar item ao carrinho");
    }
}
