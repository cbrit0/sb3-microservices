package com.cbrit0.microservices.product.dto;

import java.math.BigDecimal;

public record ProductRequest(String id, String name, String description, BigDecimal price, String skuCode) {
}
