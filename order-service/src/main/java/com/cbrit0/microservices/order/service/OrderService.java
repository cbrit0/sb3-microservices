package com.cbrit0.microservices.order.service;

import com.cbrit0.microservices.order.dto.OrderRequest;
import com.cbrit0.microservices.order.event.OrderPlacedEvent;
import com.cbrit0.microservices.order.model.Order;
import com.cbrit0.microservices.order.repository.OrderRepository;
import com.cbrit0.microservices.order.client.InventoryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {
        log.info("Start - Placing order for sku code: {}, quantity: {}", orderRequest.skuCode(), orderRequest.quantity());
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);

            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(
                    order.getOrderNumber(),
                    orderRequest.userDetails().email()
            );
            log.info("Start - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
        } else {
            throw new RuntimeException("Product with sku code " + orderRequest.skuCode() + " is not in stock");
        }
    }
}
