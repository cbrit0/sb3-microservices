package com.cbrit0.microservices.product.repository;

import com.cbrit0.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
