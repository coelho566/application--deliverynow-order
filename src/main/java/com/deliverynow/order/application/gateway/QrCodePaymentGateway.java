package com.deliverynow.order.application.gateway;

import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.infrastructure.rest.response.QrCodeResponse;

public interface QrCodePaymentGateway {

    QrCodeResponse processPayment(Order order);
}
