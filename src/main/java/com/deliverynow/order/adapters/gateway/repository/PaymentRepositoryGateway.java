package com.deliverynow.order.adapters.gateway.repository;

import com.deliverynow.order.application.gateway.PaymentGateway;
import com.deliverynow.order.application.presenter.PaymentPresenter;
import com.deliverynow.order.domain.entity.Payment;
import com.deliverynow.order.infrastructure.repository.PaymentRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PaymentRepositoryGateway implements PaymentGateway {

    PaymentRepository paymentRepository;
    PaymentPresenter paymentPresenter;

    public PaymentRepositoryGateway(PaymentRepository paymentRepository, PaymentPresenter paymentPresenter) {
        this.paymentRepository = paymentRepository;
        this.paymentPresenter = paymentPresenter;
    }

    @Override
    public void insertPayment(Payment payment) {
        paymentRepository.persist(paymentPresenter.domainToEntity(payment));
    }

    @Override
    public Optional<Payment> getByOrderId(String orderId) {

        return paymentRepository.findByOrderId(orderId)
                .map(paymentEntity -> paymentPresenter.entityToDomain(paymentEntity));
    }
}
