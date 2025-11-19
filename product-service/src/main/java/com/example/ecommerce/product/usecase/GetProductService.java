package com.example.ecommerce.product.usecase;

import com.example.ecommerce.product.domain.Product;
import com.example.ecommerce.product.port.out.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetProductService implements GetProductUseCase {
    private final ProductRepository repository;

    public GetProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> getById(UUID id) {
        return repository.findById(id);
    }
}

