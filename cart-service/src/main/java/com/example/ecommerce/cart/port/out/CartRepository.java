package com.example.ecommerce.cart.port.out;

import com.example.ecommerce.cart.domain.Cart;

import java.util.Optional;
import java.util.UUID;

/**
 * Porta de saída para persistência do carrinho.
 */
public interface CartRepository {
    Optional<Cart> findByUserId(UUID userId);
    Cart save(Cart cart);
}
