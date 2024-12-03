package com.deliverynow.order.application.controller;

import com.deliverynow.order.adapters.controller.request.OrderRequest;
import com.deliverynow.order.adapters.controller.request.OrderUpdateStatusRequest;
import com.deliverynow.order.adapters.controller.response.CheckoutResponse;
import com.deliverynow.order.adapters.controller.response.OrderResponse;

import java.util.List;

public interface OrderController {
    CheckoutResponse orderCheckout(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
    OrderResponse getResumeOrder(String id);
    void updateStatusOrder(OrderUpdateStatusRequest orderRequest);
}
