package com.deliverynow.order.application.gateway;

import com.deliverynow.order.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderGateway {

    void insertOrder(Order order);
    void updateOrder(Order order);

    Optional<Order> getOrderBySession(String session);
    List<Order> listOrders();
    Optional<Order> getOrderById(String orderId);

}
