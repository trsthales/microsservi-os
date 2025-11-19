package com.example.ecommerce.product.port.out;

import com.example.ecommerce.product.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Porta de saída para consultas de catálogo.
 */
public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(UUID id);
    Product save(Product product);
}
