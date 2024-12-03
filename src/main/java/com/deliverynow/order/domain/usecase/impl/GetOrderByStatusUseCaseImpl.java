package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.application.gateway.OrderGateway;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.usecase.GetOrderByStatusUseCase;

import java.util.List;

public class GetOrderByStatusUseCaseImpl implements GetOrderByStatusUseCase {

    OrderGateway orderGateway;

    public GetOrderByStatusUseCaseImpl(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @Override
    public List<Order> getOrderByStatus() {
        var orders = orderGateway.listOrders();
        return orders.stream()
                .filter(order -> order.getStatusOrder() != OrderStatusEnum.FINALIZADO &&
                        order.getStatusOrder() != OrderStatusEnum.AGUARDANDO_PAGAMENTO)
                .sorted(Order.COMPARATOR)
                .toList();
    }
}
