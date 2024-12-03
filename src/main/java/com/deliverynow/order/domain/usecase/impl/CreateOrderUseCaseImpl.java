package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.application.exception.OrderException;
import com.deliverynow.order.application.gateway.OrderGateway;
import com.deliverynow.order.application.presenter.OrderPresenter;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.usecase.CreateOrderUseCase;
import com.deliverynow.order.domain.usecase.ResumeOrderUseCase;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {


    OrderGateway orderGateway;
    OrderPresenter orderPresenter;
    ResumeOrderUseCase resumeOrderUseCase;

    public CreateOrderUseCaseImpl(OrderGateway orderGateway, OrderPresenter orderPresenter, ResumeOrderUseCase resumeOrderUseCase) {
        this.orderGateway = orderGateway;
        this.orderPresenter = orderPresenter;
        this.resumeOrderUseCase = resumeOrderUseCase;
    }

    @Override
    public String createdOrder(String sessionId) {

        var orderBySession = orderGateway.getOrderBySession(sessionId);

        if (orderBySession.isEmpty()) {
            var newOrder = resumeOrderUseCase.getResumeOrder(sessionId);
            newOrder.setOrderId();
            newOrder.setCreateDate();
            newOrder.setSessionId(sessionId);
            newOrder.setStatusOrder(OrderStatusEnum.AGUARDANDO_PAGAMENTO);
            orderGateway.insertOrder(newOrder);
            return newOrder.getOrderId();
        } else {
            throw new OrderException("Order already exists for the informed session");
        }
    }
}
