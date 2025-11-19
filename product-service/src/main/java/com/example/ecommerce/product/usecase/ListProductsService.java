package com.example.ecommerce.product.usecase;

import com.example.ecommerce.product.domain.Product;
import com.example.ecommerce.product.port.out.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação do caso de uso para listar produtos.
 */
@Service
public class ListProductsService implements ListProductsUseCase {

    private final ProductRepository repository;

    public ListProductsService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> list(String category, int page, int size) {
        if (page < 0 || size <= 0) throw new IllegalArgumentException("paginacao invalida");
        var all = repository.findAll();
        // filtro por categoria (case-insensitive)
        var filtered = all.stream()
                .filter(p -> category == null || category.isBlank() || p.category().equalsIgnoreCase(category))
                .sorted(java.util.Comparator.comparing(Product::name))
                .toList();
        int from = page * size;
        if (from >= filtered.size()) return java.util.List.of();
        int to = Math.min(from + size, filtered.size());
        return filtered.subList(from, to);
    }
}
