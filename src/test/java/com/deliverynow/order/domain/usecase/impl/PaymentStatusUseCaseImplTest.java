package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.application.exception.PaymentException;
import com.deliverynow.order.application.gateway.PaymentGateway;
import com.deliverynow.order.domain.entity.Payment;
import com.deliverynow.order.mock.PaymentFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
class PaymentStatusUseCaseImplTest {

    @InjectMocks
    PaymentStatusUseCaseImpl target;

    @Mock
    PaymentGateway paymentGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void mustGetPaymentByStatusTest() {
        //GIVEN
        var orderId = UUID.randomUUID().toString();
        var payment = PaymentFactory.createMockPayment();

        //WHEN
        when(paymentGateway.getByOrderId(anyString())).thenReturn(Optional.of(payment));

        //THEN
        var paymentByStatus = target.getPaymentByStatus(orderId);
        assertNotNull(paymentByStatus);
    }

    @Test
    void mustErrorPaymentNotFoundGetPaymentByStatusTest() {
        //GIVEN
        var orderId = UUID.randomUUID().toString();
        var message = String.format("Payment not found for orderId: %s", orderId);

        //WHEN
        when(paymentGateway.getByOrderId(anyString())).thenReturn(Optional.empty());

        //THEN
        var paymentException = assertThrows(PaymentException.class, () -> target.getPaymentByStatus(orderId));
        assertEquals(message, paymentException.getMessage());
    }
}