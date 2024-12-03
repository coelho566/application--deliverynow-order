package com.deliverynow.order.application.controller;

import com.deliverynow.order.adapters.controller.response.PaymentResponse;
import com.deliverynow.order.adapters.controller.response.QrCodePaymentResponse;

public interface PaymentController {

    PaymentResponse getPayment(String id);
    QrCodePaymentResponse getQrCodeOrder(String id);
    void process(String payload);
}
