package com.example.ecommerce.cart.web.dto;

import com.example.ecommerce.cart.domain.Cart;
import com.example.ecommerce.cart.domain.CartItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CartResponse {
    private String userId;
    private List<CartItemResponse> items;
    private BigDecimal total;

    public CartResponse() {}

    public CartResponse(String userId, List<CartItemResponse> items, BigDecimal total) {
        this.userId = userId;
        this.items = items;
        this.total = total;
    }

    public static CartResponse fromDomain(Cart cart) {
        List<CartItemResponse> items = cart.items().stream().map(CartItemResponse::fromDomain).collect(Collectors.toList());
        return new CartResponse(cart.userId().toString(), items, cart.total());
    }

    public String getUserId() { return userId; }
    public List<CartItemResponse> getItems() { return items; }
    public BigDecimal getTotal() { return total; }
}

