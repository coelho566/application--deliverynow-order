package com.deliverynow.order.adapters.gateway.repository;

import com.deliverynow.order.application.presenter.OrderPresenter;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.infrastructure.repository.OrderRepository;
import com.deliverynow.order.infrastructure.repository.entity.OrderEntity;
import com.deliverynow.order.mock.OrderFactory;
import io.quarkus.mongodb.panache.common.PanacheUpdate;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class OrderRepositoryGatewayTest {

    @InjectMocks
    OrderRepositoryGateway target;
    @Mock
    OrderRepository orderRepository;

    @Mock
    PanacheUpdate panacheUpdate;
    @Spy
    OrderPresenter orderPresenter  = Mappers.getMapper(OrderPresenter.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void mustInsertOrderTest() {
        //GIVEN
        var orderDomain = OrderFactory.createOrderDomain(OrderStatusEnum.RECEBIDO);

        //WHEN
        target.insertOrder(orderDomain);

        //THEN
        verify(orderRepository, times(1)).persist(Mockito.any(OrderEntity.class));
    }

    @Test
    void mustListOrdersTest() {
        //GIVEN
        var orderEntity = OrderFactory.createOrderEntity("RECEBIDO");

        //WHEN
        when(orderRepository.listAll()).thenReturn(List.of(orderEntity));
        var orders = target.listOrders();

        //THEN
        assertFalse(orders.isEmpty());
    }

    @Test
    void mustGetOrderByIdTest() {
        //GIVEN
        var orderEntity = OrderFactory.createOrderEntity("RECEBIDO");

        //WHEN
        when(orderRepository.findByOrderId(anyString())).thenReturn(Optional.of(orderEntity));
        var orderById = target.getOrderById("123");

        //THEN
        assertTrue(orderById.isPresent());
    }


    @Test
    void mustGetOrderBySessionTest() {

        //GIVEN
        var orderEntity = OrderFactory.createOrderEntity("RECEBIDO");

        //WHEN
        when(orderRepository.findBySessionId(anyString())).thenReturn(Optional.of(orderEntity));
        var orderById = target.getOrderBySession("456");

        //THEN
        assertTrue(orderById.isPresent());
    }
}