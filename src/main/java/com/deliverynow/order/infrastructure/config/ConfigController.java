package com.deliverynow.order.infrastructure.config;

import com.deliverynow.order.application.controller.OrderControllerImpl;
import com.deliverynow.order.application.controller.PaymentControllerImpl;
import com.deliverynow.order.application.presenter.OrderPresenter;
import com.deliverynow.order.application.presenter.PaymentPresenter;
import com.deliverynow.order.domain.usecase.*;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class ConfigController {

    @Default
    public OrderControllerImpl orderController(CreateOrderUseCase createOrderUseCase, ResumeOrderUseCase resumeOrderUseCase, GetOrderByStatusUseCase getOrderByStatusUseCase, UpdateStatusOrderUseCase updateStatusOrderUseCase, OrderPresenter orderPresenter) {
        return new OrderControllerImpl(createOrderUseCase, resumeOrderUseCase, getOrderByStatusUseCase, updateStatusOrderUseCase, orderPresenter);
    }

    @Default
    public PaymentControllerImpl paymentController(GenerateQrCodeUseCase qrCodeUseCase, ProcessPaymentUseCase processPaymentUseCase, PaymentStatusUseCase paymentStatusUseCase,
                                                   ValidateWebhookUseCase validateWebhookUseCase, PaymentPresenter paymentPresenter) {
        return new PaymentControllerImpl(qrCodeUseCase, processPaymentUseCase, paymentStatusUseCase, validateWebhookUseCase, paymentPresenter);
    }
}
