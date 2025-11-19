package com.example.ecommerce.product.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductResponseDto {
    private UUID id;
    private String name;
    private String category;
    private BigDecimal price;

    public ProductResponseDto() {}

    public ProductResponseDto(UUID id, String name, String category, BigDecimal price) {
        this.id = id; this.name = name; this.category = category; this.price = price;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }

    public static ProductResponseDto fromDomain(com.example.ecommerce.product.domain.Product p) {
        return new ProductResponseDto(p.id(), p.name(), p.category(), p.price());
    }
}

