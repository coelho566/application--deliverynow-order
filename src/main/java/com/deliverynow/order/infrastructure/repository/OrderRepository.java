package com.deliverynow.order.infrastructure.repository;


import com.deliverynow.order.infrastructure.repository.entity.OrderEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<OrderEntity> {

    public Optional<OrderEntity> findByOrderId(String orderId) {
        return find("orderId", orderId).firstResultOptional();
    }

    public Optional<OrderEntity> findBySessionId(String sessionIs) {
        return find("sessionId", sessionIs).firstResultOptional();
    }
}
