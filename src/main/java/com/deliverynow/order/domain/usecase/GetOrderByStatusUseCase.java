package com.deliverynow.order.domain.usecase;

import com.deliverynow.order.domain.entity.Order;

import java.util.List;

public interface GetOrderByStatusUseCase {

    List<Order> getOrderByStatus();
}
