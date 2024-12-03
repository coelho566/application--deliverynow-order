package com.deliverynow.order.infrastructure.config;


import com.deliverynow.order.adapters.gateway.repository.PaymentRepositoryGateway;
import com.deliverynow.order.application.gateway.*;
import com.deliverynow.order.application.presenter.ItemOrderPresenter;
import com.deliverynow.order.application.presenter.OrderPresenter;
import com.deliverynow.order.application.presenter.PaymentPresenter;
import com.deliverynow.order.domain.usecase.ResumeOrderUseCase;
import com.deliverynow.order.domain.usecase.UpdateStatusOrderUseCase;
import com.deliverynow.order.domain.usecase.impl.*;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
public class ConfigUseCase {

    @Default
    public ResumeOrderUseCaseImpl resumeOrderUseCase(ItemGateway itemGateway, CustomerGateway customerGateway, ItemOrderPresenter itemOrderPresenter, OrderPresenter orderPresenter) {
        return new ResumeOrderUseCaseImpl(itemGateway, customerGateway, itemOrderPresenter, orderPresenter);
    }

    @Default
    public CreateOrderUseCaseImpl createOrderUseCase(OrderGateway orderGateway, OrderPresenter orderPresenter, ResumeOrderUseCase resumeOrderUseCase) {
        return new CreateOrderUseCaseImpl(orderGateway, orderPresenter, resumeOrderUseCase);
    }

    @Default
    public GetOrderByStatusUseCaseImpl getOrderByStatusUseCase(OrderGateway orderGateway) {
        return new GetOrderByStatusUseCaseImpl(orderGateway);
    }

    @Default
    public GenerateQrCodeUseCaseImpl generateQrCodeUseCase(OrderGateway orderGateway, QrCodePaymentGateway qrCodePaymentGateway) {
        return new GenerateQrCodeUseCaseImpl(orderGateway, qrCodePaymentGateway);
    }

    @Default
    public ProcessPaymentUseCaseImpl processPaymentUseCase(PaymentPresenter paymentPresenter, ProcessPaymentGateway processPaymentGateway, UpdateStatusOrderUseCase updateStatusOrderUseCase, PaymentRepositoryGateway paymentRepositoryGateway) {
        return new ProcessPaymentUseCaseImpl(paymentPresenter, processPaymentGateway, updateStatusOrderUseCase, paymentRepositoryGateway);
    }

    @Default
    public UpdateStatusOrderUseCaseImpl updateStatusOrderUseCase(OrderGateway orderGateway) {
        return new UpdateStatusOrderUseCaseImpl(orderGateway);
    }

    @Default
    public PaymentStatusUseCaseImpl paymentStatusUseCase(PaymentGateway paymentGateway) {
        return new PaymentStatusUseCaseImpl(paymentGateway);
    }

    @Default
    public ValidateWebhookUseCaseImpl validateWebhookUseCase(OrderProperties orderProperties){
        return new ValidateWebhookUseCaseImpl(orderProperties);
    }
}
