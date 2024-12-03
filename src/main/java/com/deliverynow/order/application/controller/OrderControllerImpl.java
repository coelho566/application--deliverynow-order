package com.deliverynow.order.application.controller;


import com.deliverynow.order.adapters.controller.request.OrderRequest;
import com.deliverynow.order.adapters.controller.request.OrderUpdateStatusRequest;
import com.deliverynow.order.adapters.controller.response.CheckoutResponse;
import com.deliverynow.order.adapters.controller.response.OrderResponse;
import com.deliverynow.order.application.presenter.OrderPresenter;
import com.deliverynow.order.domain.usecase.CreateOrderUseCase;
import com.deliverynow.order.domain.usecase.GetOrderByStatusUseCase;
import com.deliverynow.order.domain.usecase.ResumeOrderUseCase;
import com.deliverynow.order.domain.usecase.UpdateStatusOrderUseCase;

import java.util.List;

public class OrderControllerImpl implements OrderController{

    CreateOrderUseCase createOrderUseCase;
    ResumeOrderUseCase resumeOrderUseCase;
    GetOrderByStatusUseCase getOrderByStatusUseCase;
    UpdateStatusOrderUseCase updateStatusOrderUseCase;
    OrderPresenter orderPresenter;

    public OrderControllerImpl(CreateOrderUseCase createOrderUseCase, ResumeOrderUseCase resumeOrderUseCase, GetOrderByStatusUseCase getOrderByStatusUseCase, UpdateStatusOrderUseCase updateStatusOrderUseCase, OrderPresenter orderPresenter) {
        this.createOrderUseCase = createOrderUseCase;
        this.resumeOrderUseCase = resumeOrderUseCase;
        this.getOrderByStatusUseCase = getOrderByStatusUseCase;
        this.updateStatusOrderUseCase = updateStatusOrderUseCase;
        this.orderPresenter = orderPresenter;
    }

    @Override
    public CheckoutResponse orderCheckout(OrderRequest orderRequest) {
        var orderId = createOrderUseCase.createdOrder(orderRequest.getSessionId());
        return CheckoutResponse.builder().orderId(orderId).build();
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        var orders = getOrderByStatusUseCase.getOrderByStatus();
        return orders.stream().map(order -> orderPresenter.domainToResponse(order)).toList();
    }

    @Override
    public OrderResponse getResumeOrder(String id) {
        var resumeOrder = resumeOrderUseCase.getResumeOrder(id);
        return orderPresenter.domainToResponse(resumeOrder);
    }

    @Override
    public void updateStatusOrder(OrderUpdateStatusRequest orderRequest) {
        updateStatusOrderUseCase.updateStatus(orderRequest.getOrderId(), orderRequest.getStatus());
    }
}

