package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.application.exception.OrderException;
import com.deliverynow.order.application.gateway.OrderGateway;
import com.deliverynow.order.application.presenter.OrderPresenter;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.usecase.ResumeOrderUseCase;
import com.deliverynow.order.mock.OrderFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@QuarkusTest
class CreateOrderUseCaseImplTest {
    @InjectMocks
    CreateOrderUseCaseImpl target;
    @Mock
    OrderGateway orderGateway;
    @Mock
    ResumeOrderUseCase resumeOrderUseCase;
    @Spy
    OrderPresenter orderPresenter = Mappers.getMapper(OrderPresenter.class);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustCreatedOrderTest() {
        //GIVEN
        var sessionId = UUID.randomUUID().toString();
        var resumeOrder = OrderFactory.createOrderDomain(OrderStatusEnum.RECEBIDO);

        //WHEN
        when(orderGateway.getOrderBySession(anyString())).thenReturn(Optional.empty());
        when(resumeOrderUseCase.getResumeOrder(anyString())).thenReturn(resumeOrder);

        var order = target.createdOrder(sessionId);

        //THEN
        verify(orderGateway, times(1)).insertOrder(any());
        assertNotNull(order);
    }

    @Test
    void mustErrorCreatedOrderTest() {
        //GIVEN
        var sessionId = UUID.randomUUID().toString();
        var resumeOrder = OrderFactory.createOrderDomain(OrderStatusEnum.RECEBIDO);

        //WHEN
        when(orderGateway.getOrderBySession(anyString())).thenReturn(Optional.of(resumeOrder));

        var orderException = assertThrows(OrderException.class, () -> target.createdOrder(sessionId));

        //THEN
        verify(orderGateway, times(0)).insertOrder(any());
        assertEquals("Order already exists for the informed session", orderException.getMessage());
    }

}