package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.application.gateway.OrderGateway;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.mock.OrderFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class GetOrderByStatusUseCaseImplTest {

    @InjectMocks
    GetOrderByStatusUseCaseImpl target;

    @Mock
    OrderGateway orderGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void mustGetOrderByStatusTest() {
        //GIVEN
        var order = OrderFactory.createOrderDomain(OrderStatusEnum.EM_PREPARACAO);
        var order2 = OrderFactory.createOrderDomain(OrderStatusEnum.FINALIZADO);

        //WHEN
        Mockito.when(orderGateway.listOrders()).thenReturn(List.of(order, order2));

        //THEN
        var orderByStatus = target.getOrderByStatus();

        assertEquals(1, orderByStatus.size());
        assertEquals(OrderStatusEnum.EM_PREPARACAO, orderByStatus.getFirst().getStatusOrder());
    }
}