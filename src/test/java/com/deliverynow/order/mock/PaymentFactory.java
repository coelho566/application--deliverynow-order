package com.deliverynow.order.mock;

import com.deliverynow.order.adapters.controller.response.PaymentResponse;
import com.deliverynow.order.domain.entity.Payment;
import com.deliverynow.order.infrastructure.repository.entity.PaymentEntity;

public class PaymentFactory {

    public static Payment createMockPayment() {
        return Payment.builder()
                .amountRefunded("0.0")
                .currencyId("BRL")
                .dateApproved("2024-07-29T19:56:46.000-04:00")
                .dateCreated("2024-07-29T19:56:46.000-04:00")
                .lastModified("2024-07-29T19:56:46.000-04:00")
                .operationType("regular_payment")
                .orderId("35138389")
                .shippingCost("0.0")
                .status("approved")
                .statusDetail("accredited")
                .totalPaidAmount("60.0")
                .transactionAmount("60.0")
                .build();
    }

    public static PaymentEntity createPaymentEntity() {
        return PaymentEntity.builder()
                .amountRefunded("0.0")
                .currencyId("BRL")
                .dateApproved("2024-07-29T19:56:46.000-04:00")
                .dateCreated("2024-07-29T19:56:46.000-04:00")
                .lastModified("2024-07-29T19:56:46.000-04:00")
                .operationType("regular_payment")
                .orderId("35138389")
                .shippingCost("0.0")
                .status("approved")
                .statusDetail("accredited")
                .totalPaidAmount("60.0")
                .transactionAmount("60.0")
                .build();
    }

    public static PaymentResponse createPaymentResponse() {
        return PaymentResponse.builder()
                .amountRefunded("0.0")
                .currencyId("BRL")
                .dateApproved("2024-07-29T19:56:46.000-04:00")
                .dateCreated("2024-07-29T19:56:46.000-04:00")
                .lastModified("2024-07-29T19:56:46.000-04:00")
                .operationType("regular_payment")
                .orderId("35138389")
                .shippingCost("0.0")
                .status("approved")
                .statusDetail("accredited")
                .totalPaidAmount("60.0")
                .transactionAmount("60.0")
                .build();
    }
}
