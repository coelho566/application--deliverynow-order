package com.deliverynow.order.domain.usecase;

import com.deliverynow.order.domain.entity.Order;

public interface ResumeOrderUseCase {

    Order getResumeOrder(String customerId);
}
