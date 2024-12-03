package com.deliverynow.order.adapters.gateway.repository;


import com.deliverynow.order.application.gateway.OrderGateway;
import com.deliverynow.order.application.presenter.OrderPresenter;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.infrastructure.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderRepositoryGateway implements OrderGateway {

    OrderRepository orderRepository;
    OrderPresenter orderPresenter;

    public OrderRepositoryGateway(OrderRepository orderRepository, OrderPresenter orderPresenter) {
        this.orderRepository = orderRepository;
        this.orderPresenter = orderPresenter;
    }

    @Override
    public void insertOrder(Order order) {
        var newOrder = orderPresenter.domainToEntity(order);
        orderRepository.persist(newOrder);
    }

    @Override
    public List<Order> listOrders() {
        var orderEntities = orderRepository.listAll();
        return orderEntities.stream()
                .map(order -> orderPresenter.entityToDomain(order)).toList();
    }

    @Override
    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findByOrderId(orderId)
                .map(orderEntity -> orderPresenter.entityToDomain(orderEntity));
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.update("statusOrder", order.getStatusOrder())
                .where("orderId", order.getOrderId());
    }

    @Override
    public Optional<Order> getOrderBySession(String session) {
        return orderRepository.findBySessionId(session)
                .map(orderEntity -> orderPresenter.entityToDomain(orderEntity));
    }
}
