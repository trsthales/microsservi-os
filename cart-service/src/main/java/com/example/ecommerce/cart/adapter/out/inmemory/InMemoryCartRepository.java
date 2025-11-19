package com.example.ecommerce.cart.adapter.out.inmemory;

import com.example.ecommerce.cart.domain.Cart;
import com.example.ecommerce.cart.port.out.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCartRepository implements CartRepository {
    private final ConcurrentHashMap<UUID, Cart> store = new ConcurrentHashMap<>();

    @Override
    public Optional<Cart> findByUserId(UUID userId) {
        return Optional.ofNullable(store.get(userId));
    }

    @Override
    public Cart save(Cart cart) {
        store.put(cart.userId(), cart);
        return cart;
    }
}

