package com.example.ecommerce.cart.usecase;

import com.example.ecommerce.cart.domain.Cart;
import com.example.ecommerce.cart.port.out.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes TDD para adicionar item ao carrinho.
 */
class AddItemToCartUseCaseTest {

    private AddItemToCartUseCase useCase;
    private InMemoryCartRepo repo;
    private UUID userId;

    @BeforeEach
    void setup() {
        repo = new InMemoryCartRepo();
        useCase = new AddItemToCartService(repo);
        userId = UUID.randomUUID();
    }

    @Test
    void naoDevePermitirQuantidadeZeroOuNegativa() {
        assertThrows(IllegalArgumentException.class, () ->
                useCase.add(userId, UUID.randomUUID(), 0, BigDecimal.TEN));
        assertThrows(IllegalArgumentException.class, () ->
                useCase.add(userId, UUID.randomUUID(), -1, BigDecimal.TEN));
    }

    @Test
    void deveSomarQuantidadeParaMesmoProduto() {
        UUID product = UUID.randomUUID();
        useCase.add(userId, product, 1, BigDecimal.TEN);
        useCase.add(userId, product, 2, BigDecimal.TEN);
        Cart c = repo.findByUserId(userId).orElseThrow();
        assertEquals(1, c.items().size());
        assertEquals(3, c.items().iterator().next().quantity());
    }

    @Test
    void deveCalcularTotal() {
        useCase.add(userId, UUID.randomUUID(), 2, new BigDecimal("5.00"));
        useCase.add(userId, UUID.randomUUID(), 1, new BigDecimal("7.50"));
        Cart c = repo.findByUserId(userId).orElseThrow();
        assertEquals(new BigDecimal("17.50"), c.total());
    }

    static class InMemoryCartRepo implements CartRepository {
        private final Map<UUID, Cart> store = new HashMap<>();

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
}
