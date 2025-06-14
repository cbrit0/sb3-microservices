package com.cbrit0.microservices.product.service;

import com.cbrit0.microservices.product.dto.ProductRequest;
import com.cbrit0.microservices.product.dto.ProductResponse;
import com.cbrit0.microservices.product.model.Product;
import com.cbrit0.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .skuCode(productRequest.skuCode())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getName());
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getSkuCode());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getSkuCode()))
                .toList();
    }
}
