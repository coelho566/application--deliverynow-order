package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.adapters.controller.response.QrCodePaymentResponse;
import com.deliverynow.order.application.exception.OrderException;
import com.deliverynow.order.application.gateway.OrderGateway;
import com.deliverynow.order.application.gateway.QrCodePaymentGateway;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.usecase.GenerateQrCodeUseCase;

import java.time.LocalDateTime;

public class GenerateQrCodeUseCaseImpl implements GenerateQrCodeUseCase {

    OrderGateway orderGateway;
    QrCodePaymentGateway qrCodePaymentGateway;

    public GenerateQrCodeUseCaseImpl(OrderGateway orderGateway, QrCodePaymentGateway qrCodePaymentGateway) {
        this.orderGateway = orderGateway;
        this.qrCodePaymentGateway = qrCodePaymentGateway;
    }

    @Override
    public QrCodePaymentResponse getQrCode(String orderId) {
        var order = orderGateway.getOrderById(orderId);

        return order.map(o -> {
                    if (o.getStatusOrder() == OrderStatusEnum.AGUARDANDO_PAGAMENTO) {

                        var qrCodeResponse = qrCodePaymentGateway.processPayment(o);
                        return QrCodePaymentResponse.builder()
                                .dateExpiration(LocalDateTime.now().plusMinutes(10L).toString())
                                .qrCode(qrCodeResponse.getQrData())
                                .inStoreOrderId(qrCodeResponse.getInStoreOrderId())
                                .build();
                    } else {
                        throw new OrderException(String.format("Payment already made for the order id %s entered", orderId));
                    }
                }
        ).orElseThrow(() -> new OrderException("Order not found to generate qrcode"));
    }
}
