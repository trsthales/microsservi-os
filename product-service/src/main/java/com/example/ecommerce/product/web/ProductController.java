package com.example.ecommerce.product.web;

import com.example.ecommerce.product.usecase.GetProductUseCase;
import com.example.ecommerce.product.usecase.ListProductsUseCase;
import com.example.ecommerce.product.web.dto.ProductResponseDto;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ListProductsUseCase listUseCase;
    private final GetProductUseCase getUseCase;

    public ProductController(ListProductsUseCase listUseCase, GetProductUseCase getUseCase) {
        this.listUseCase = listUseCase;
        this.getUseCase = getUseCase;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> list(@RequestParam(required = false) String category,
                                                          @RequestParam(defaultValue = "0") @Min(0) int page,
                                                          @RequestParam(defaultValue = "10") @Min(1) int size) {
        var products = listUseCase.list(category, page, size);
        var dto = products.stream().map(ProductResponseDto::fromDomain).toList();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> get(@PathVariable("id") UUID id) {
        return getUseCase.getById(id)
                .map(ProductResponseDto::fromDomain)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
