package com.example.ecommerce.cart.web.dto;

import com.example.ecommerce.cart.domain.CartItem;

import java.math.BigDecimal;
import java.util.UUID;

public class CartItemResponse {
    private UUID productId;
    private int quantity;
    private BigDecimal priceSnapshot;
    private BigDecimal subtotal;

    public CartItemResponse() {}

    public CartItemResponse(UUID productId, int quantity, BigDecimal priceSnapshot, BigDecimal subtotal) {
        this.productId = productId;
        this.quantity = quantity;
        this.priceSnapshot = priceSnapshot;
        this.subtotal = subtotal;
    }

    public static CartItemResponse fromDomain(CartItem item) {
        return new CartItemResponse(item.productId(), item.quantity(), item.priceSnapshot(), item.subtotal());
    }

    public UUID getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public BigDecimal getPriceSnapshot() { return priceSnapshot; }
    public BigDecimal getSubtotal() { return subtotal; }
}
