package com.deliverynow.order.infrastructure.repository;


import com.deliverynow.order.infrastructure.repository.entity.PaymentEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PaymentRepository implements PanacheMongoRepository<PaymentEntity> {

    public Optional<PaymentEntity> findByOrderId(String orderId) {
        return find("orderId", orderId).firstResultOptional();
    }
}
