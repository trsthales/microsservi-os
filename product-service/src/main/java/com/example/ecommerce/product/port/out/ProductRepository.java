package com.example.ecommerce.product.port.out;

import com.example.ecommerce.product.domain.Product;

import java.util.List;

/**
 * Porta de saída para consultas de catálogo.
 */
public interface ProductRepository {
    List<Product> findAll();
}
