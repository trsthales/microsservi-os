package com.example.ecommerce.product.adapter.inmemory;

import com.example.ecommerce.product.domain.Product;
import com.example.ecommerce.product.port.out.ProductRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final ConcurrentHashMap<UUID, Product> store = new ConcurrentHashMap<>();

    public InMemoryProductRepository() {
        // seed sample data
        var p1 = new Product(UUID.randomUUID(), "Apple iPhone 15", "phones", new BigDecimal("799.99"));
        var p2 = new Product(UUID.randomUUID(), "Samsung Galaxy S24", "phones", new BigDecimal("699.99"));
        var p3 = new Product(UUID.randomUUID(), "Sony WH-1000XM5", "audio", new BigDecimal("349.00"));
        save(p1); save(p2); save(p3);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Product save(Product product) {
        store.put(product.id(), product);
        return product;
    }
}

