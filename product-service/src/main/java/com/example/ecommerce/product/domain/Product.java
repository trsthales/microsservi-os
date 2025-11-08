package com.example.ecommerce.product.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Entidade de domínio Produto.
 * Mantida sem anotações para preservar independência da camada de persistência.
 */
public class Product {
    private final UUID id;
    private final String name;
    private final String category;
    private final BigDecimal price;

    public Product(UUID id, String name, String category, BigDecimal price) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = validateName(name);
        this.category = category == null ? "" : category.toLowerCase();
        this.price = validatePrice(price);
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("nome produto vazio");
        return name.trim();
    }

    private BigDecimal validatePrice(BigDecimal price) {
        if (price == null || price.signum() <= 0) throw new IllegalArgumentException("preço invalido");
        return price;
    }

    public UUID id() { return id; }
    public String name() { return name; }
    public String category() { return category; }
    public BigDecimal price() { return price; }
}
