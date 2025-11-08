package com.example.ecommerce.product.usecase;

import com.example.ecommerce.product.domain.Product;

import java.util.List;

/**
 * Caso de uso para listar produtos com filtros simples.
 */
public interface ListProductsUseCase {
    List<Product> list(String category, int page, int size);
}
