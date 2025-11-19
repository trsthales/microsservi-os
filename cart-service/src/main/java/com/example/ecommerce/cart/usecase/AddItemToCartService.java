package com.example.ecommerce.cart.usecase;

import com.example.ecommerce.cart.domain.Cart;
import com.example.ecommerce.cart.port.out.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Implementação do caso de uso de adicionar item ao carrinho.
 */
@Service
public class AddItemToCartService implements AddItemToCartUseCase {

    private final CartRepository repository;

    public AddItemToCartService(CartRepository repository) {
        this.repository = Objects.requireNonNull(repository, "repository");
    }

    @Override
    public void add(UUID userId, UUID productId, int quantity, BigDecimal priceSnapshot) {
        Objects.requireNonNull(userId, "userId");
        Objects.requireNonNull(productId, "productId");
        Objects.requireNonNull(priceSnapshot, "priceSnapshot");

        if (quantity <= 0) throw new IllegalArgumentException("quantidade deve ser > 0");

        // Busca carrinho existente ou cria um novo
        Cart cart = repository.findByUserId(userId).orElseGet(() -> new Cart(userId));

        // Adiciona item (a lógica do agregado somará quantidades se já existir)
        cart.addItem(productId, quantity, priceSnapshot);

        // Persiste o estado atualizado
        repository.save(cart);
    }
}
