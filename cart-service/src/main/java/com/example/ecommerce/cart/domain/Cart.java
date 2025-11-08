package com.example.ecommerce.cart.domain;

import java.math.BigDecimal;
import java.util.*;

/**
 * Agregado Carrinho.
 * Regras a serem exercitadas via TDD nos casos de uso:
 * - Adicionar item (somar quantidade se já existe)
 * - Remover item
 * - Calcular total
 */
public class Cart {
    private final UUID userId;
    private final Map<UUID, CartItem> items = new LinkedHashMap<>();

    public Cart(UUID userId) {
        this.userId = Objects.requireNonNull(userId, "userId");
    }

    public UUID userId() { return userId; }

    public Collection<CartItem> items() { return Collections.unmodifiableCollection(items.values()); }

    public void addItem(UUID productId, int quantity, BigDecimal priceSnapshot) {
        CartItem existing = items.get(productId);
        if (existing == null) {
            items.put(productId, new CartItem(productId, quantity, priceSnapshot));
        } else {
            existing.addQuantity(quantity);
        }
    }

    public void removeItem(UUID productId) {
        items.remove(productId);
    }

    public BigDecimal total() {
        return items.values().stream()
                .map(CartItem::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isEmpty() { return items.isEmpty(); }
}
