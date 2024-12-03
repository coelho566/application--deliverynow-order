package com.deliverynow.order.domain.entity;

public enum PaymentEnum {

    APROVADO,
    REPROVADO,
    PENDENTE;

    public static PaymentEnum getValue(String code) {
        for (PaymentEnum paymentEnum : PaymentEnum.values()) {
            if (paymentEnum.name().equals(code)) {
                return paymentEnum;
            }
        }
        return PaymentEnum.PENDENTE;
    }
}
