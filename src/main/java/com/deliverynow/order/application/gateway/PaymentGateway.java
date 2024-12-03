package com.deliverynow.order.application.gateway;

import com.deliverynow.order.domain.entity.Payment;

import java.util.Optional;

public interface PaymentGateway {

    void insertPayment(Payment payment);
    Optional<Payment> getByOrderId(String orderId);
}
