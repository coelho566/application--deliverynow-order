package com.deliverynow.order.domain.entity;

import com.deliverynow.order.application.exception.OrderException;

public enum OrderStatusEnum {

    RECEBIDO,
    AGUARDANDO_PAGAMENTO,
    EM_PREPARACAO,
    PRONTO,
    FINALIZADO;

    public static OrderStatusEnum getStatus(String statusName) {
        try {
            return OrderStatusEnum.valueOf(statusName.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new OrderException("Order status not found: " + statusName);
        }
    }
}
