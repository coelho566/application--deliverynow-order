package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.application.exception.OrderException;
import com.deliverynow.order.application.gateway.OrderGateway;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.mock.OrderFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@QuarkusTest
class UpdateStatusOrderUseCaseImplTest {

    @InjectMocks
    UpdateStatusOrderUseCaseImpl target;
    @Mock
    OrderGateway orderGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustUpdateStatusTest() {
        //GIVEN
        var status = "RECEBIDO";
        var orderId = "123456";
        var order = OrderFactory.createOrderDomain(OrderStatusEnum.RECEBIDO);

        //WHEN
        Mockito.when(orderGateway.getOrderById(anyString())).thenReturn(Optional.of(order));
        target.updateStatus(orderId, status);

        //THEN
        verify(orderGateway, times(1)).updateOrder(any());
    }

    @Test
    void mustErrorPaymentNotFoundUpdateStatusTest() {
        //GIVEN
        var status = "RECEBIDO";
        var orderId = "123456";

        //WHEN
        when(orderGateway.getOrderById(anyString())).thenReturn(Optional.empty());
        var paymentException = assertThrows(OrderException.class, () -> target.updateStatus(orderId, status));

        //THEN
        assertEquals("Order not found for update", paymentException.getMessage());
    }
}