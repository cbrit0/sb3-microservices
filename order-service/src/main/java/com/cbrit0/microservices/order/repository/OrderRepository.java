package com.cbrit0.microservices.order.repository;

import com.cbrit0.microservices.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
