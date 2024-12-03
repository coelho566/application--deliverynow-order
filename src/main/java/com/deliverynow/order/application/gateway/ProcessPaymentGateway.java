package com.deliverynow.order.application.gateway;

import com.deliverynow.order.infrastructure.rest.response.MerchantOrderResponse;

public interface ProcessPaymentGateway {

    MerchantOrderResponse processPayment(String orderId);
}
