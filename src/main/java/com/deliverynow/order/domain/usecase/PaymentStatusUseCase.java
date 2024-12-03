package com.deliverynow.order.domain.usecase;

import com.deliverynow.order.domain.entity.Payment;

public interface PaymentStatusUseCase {

    Payment getPaymentByStatus(String orderId);
}
