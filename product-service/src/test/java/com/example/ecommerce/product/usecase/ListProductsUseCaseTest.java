package com.example.ecommerce.product.usecase;

import com.example.ecommerce.product.domain.Product;
import com.example.ecommerce.product.port.out.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes TDD para listagem de produtos (ordenacao, filtro, paginacao).
 */
class ListProductsUseCaseTest {

    private ListProductsUseCase useCase;
    private InMemoryProductRepo repo;

    @BeforeEach
    void setup() {
        repo = new InMemoryProductRepo();
        // adiciona produtos embaralhados
        repo.add("Camiseta", "roupas", 50);
        repo.add("Caneca", "utilidades", 30);
        repo.add("Bone", "roupas", 40);
        useCase = new ListProductsService(repo);
    }

    @Test
    void deveOrdenarPorNome() {
        List<Product> list = useCase.list(null, 0, 10);
        assertEquals(List.of("Bone", "Camiseta", "Caneca"), list.stream().map(Product::name).toList());
    }

    @Test
    void deveFiltrarPorCategoria() {
        List<Product> list = useCase.list("roupas", 0, 10);
        assertEquals(2, list.size());
        assertTrue(list.stream().allMatch(p -> p.category().equals("roupas")));
    }

    @Test
    void devePaginacaoSimples() {
        List<Product> page0 = useCase.list(null, 0, 2);
        List<Product> page1 = useCase.list(null, 1, 2);
        assertEquals(2, page0.size());
        assertEquals(1, page1.size());
    }

    static class InMemoryProductRepo implements ProductRepository {
        private final List<Product> data = new ArrayList<>();

        void add(String name, String category, double price) {
            data.add(new Product(UUID.randomUUID(), name, category, BigDecimal.valueOf(price)));
        }

        @Override
        public List<Product> findAll() { return Collections.unmodifiableList(data); }
    }
}
