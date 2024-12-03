package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.application.exception.PaymentException;
import com.deliverynow.order.application.gateway.PaymentGateway;
import com.deliverynow.order.domain.entity.Payment;
import com.deliverynow.order.domain.usecase.PaymentStatusUseCase;

public class PaymentStatusUseCaseImpl implements PaymentStatusUseCase {

    PaymentGateway paymentGateway;

    public PaymentStatusUseCaseImpl(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public Payment getPaymentByStatus(String orderId) {
        return paymentGateway.getByOrderId(orderId)
                .orElseThrow(() -> new PaymentException(String.format("Payment not found for orderId: %s", orderId)));
    }
}
