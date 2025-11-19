package com.example.ecommerce.product.usecase;

import com.example.ecommerce.product.domain.Product;

import java.util.Optional;
import java.util.UUID;

public interface GetProductUseCase {
    Optional<Product> getById(UUID id);
}

