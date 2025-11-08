package com.example.ecommerce.cart.usecase;

import java.util.UUID;
import java.math.BigDecimal;

/**
 * Caso de uso para adicionar item ao carrinho.
 * Regras esperadas (testes virão para guiar):
 * - Quantidade > 0
 * - Se produto já existe, soma quantidade
 * - Capturar preço (snapshot) informado
 * - Criar carrinho se não existir
 */
public interface AddItemToCartUseCase {
    void add(UUID userId, UUID productId, int quantity, BigDecimal priceSnapshot);
}
