package com.example.ecommerce.cart.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Item de carrinho.
 * - priceSnapshot: preço capturado no momento da adição (evita variações inesperadas no total).
 */
public class CartItem {
    private final UUID productId;
    private int quantity;
    private final BigDecimal priceSnapshot;

    public CartItem(UUID productId, int quantity, BigDecimal priceSnapshot) {
        this.productId = Objects.requireNonNull(productId, "productId");
        setQuantity(quantity);
        this.priceSnapshot = Objects.requireNonNull(priceSnapshot, "priceSnapshot");
    }

    public UUID productId() { return productId; }
    public int quantity() { return quantity; }
    public BigDecimal priceSnapshot() { return priceSnapshot; }

    public void addQuantity(int delta) {
        setQuantity(this.quantity + delta);
    }

    private void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantidade deve ser > 0");
        this.quantity = quantity;
    }

    public BigDecimal subtotal() { return priceSnapshot.multiply(BigDecimal.valueOf(quantity)); }
}
