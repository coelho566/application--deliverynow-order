package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.adapters.controller.response.QrCodePaymentResponse;
import com.deliverynow.order.application.exception.OrderException;
import com.deliverynow.order.application.gateway.OrderGateway;
import com.deliverynow.order.application.gateway.QrCodePaymentGateway;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.infrastructure.rest.response.QrCodeResponse;
import com.deliverynow.order.mock.OrderFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class GenerateQrCodeUseCaseImplTest {

    @InjectMocks
    GenerateQrCodeUseCaseImpl target;
    @Mock
    OrderGateway orderGateway;
    @Mock
    QrCodePaymentGateway qrCodePaymentGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustGetQrCodeTest() {
        //GIVEN
        var order = OrderFactory.createOrderDomain(OrderStatusEnum.AGUARDANDO_PAGAMENTO);
        var qrCodeResponse = new QrCodeResponse();
        qrCodeResponse.setInStoreOrderId(UUID.randomUUID().toString());
        qrCodeResponse.setQrData("aswwdfd");

        //WHEN
        when(orderGateway.getOrderById(any())).thenReturn(Optional.of(order));
        when(qrCodePaymentGateway.processPayment(any())).thenReturn(qrCodeResponse);

        //THEN
        var qrCode = target.getQrCode(order.getOrderId());
        assertNotNull(qrCode);
        assertNotNull(qrCode.getDateExpiration());
    }

    @Test
    void mustErrorOrderNotFoundGetQrCodeTest() {
        //GIVEN
        var order = OrderFactory.createOrderDomain(OrderStatusEnum.AGUARDANDO_PAGAMENTO);

        //WHEN
        when(orderGateway.getOrderById(any())).thenReturn(Optional.empty());

        //THEN
        var orderException = assertThrows(OrderException.class, () -> target.getQrCode(order.getOrderId()));

        assertEquals("Order not found to generate qrcode", orderException.getMessage());
    }

    @Test
    void mustErrorPaymentAlreadyGetQrCodeTest() {
        //GIVEN
        var order = OrderFactory.createOrderDomain(OrderStatusEnum.FINALIZADO);
        var message = String.format("Payment already made for the order id %s entered", order.getOrderId());

        //WHEN
        when(orderGateway.getOrderById(any())).thenReturn(Optional.of(order));

        //THEN
        var orderException = assertThrows(OrderException.class, () -> target.getQrCode(order.getOrderId()));

        assertEquals(message, orderException.getMessage());

    }
}