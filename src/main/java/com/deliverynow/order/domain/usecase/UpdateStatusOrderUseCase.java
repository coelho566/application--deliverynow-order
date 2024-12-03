package com.deliverynow.order.domain.usecase;

public interface UpdateStatusOrderUseCase {

    void updateStatus(String orderId, String status);
}
