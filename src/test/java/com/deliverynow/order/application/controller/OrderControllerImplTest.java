package com.deliverynow.order.application.controller;

import com.deliverynow.order.adapters.controller.request.OrderRequest;
import com.deliverynow.order.adapters.controller.request.OrderUpdateStatusRequest;
import com.deliverynow.order.adapters.controller.response.CheckoutResponse;
import com.deliverynow.order.adapters.controller.response.OrderResponse;
import com.deliverynow.order.application.presenter.OrderPresenter;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.usecase.CreateOrderUseCase;
import com.deliverynow.order.domain.usecase.GetOrderByStatusUseCase;
import com.deliverynow.order.domain.usecase.ResumeOrderUseCase;
import com.deliverynow.order.domain.usecase.UpdateStatusOrderUseCase;
import com.deliverynow.order.mock.OrderFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@QuarkusTest
class OrderControllerImplTest {

    @InjectMocks
    OrderControllerImpl target;
    @Mock
    CreateOrderUseCase createOrderUseCase;
    @Mock
    ResumeOrderUseCase resumeOrderUseCase;
    @Mock
    GetOrderByStatusUseCase getOrderByStatusUseCase;
    @Mock
    UpdateStatusOrderUseCase updateStatusOrderUseCase;
    @Spy
    OrderPresenter orderPresenter = Mappers.getMapper(OrderPresenter.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustOrderCheckoutTest() {
        //GIVEN
        var orderRequest = OrderRequest.builder()
                .sessionId(UUID.randomUUID().toString())
                .build();

        //WHEN
        when(createOrderUseCase.createdOrder(anyString())).thenReturn("123456");
        var checkoutResponse = target.orderCheckout(orderRequest);

        //THEN
        assertNotNull(checkoutResponse);
        assertEquals("123456", checkoutResponse.getOrderId());
    }

    @Test
    void mustGetAllOrdersTest() {
        //GIVEN
        var order = OrderFactory.createOrderDomain(OrderStatusEnum.RECEBIDO);

        //WHEN
        when(getOrderByStatusUseCase.getOrderByStatus()).thenReturn(List.of(order));
        var allOrders = target.getAllOrders();

        //THEN
        assertEquals(1, allOrders.size());
    }

    @Test
    void mustGetResumeOrderTest() {
        //GIVEN
        var orderId = "123";
        var order = OrderFactory.createOrderDomain(OrderStatusEnum.RECEBIDO);

        //WHEN
        when(resumeOrderUseCase.getResumeOrder(anyString())).thenReturn(order);
        var resumeOrder = target.getResumeOrder(orderId);

        //THEN
        assertNotNull(resumeOrder);
    }

    @Test
    void mustUpdateStatusOrderTest() {
        //GIVEN
        var orderUpdate = OrderUpdateStatusRequest.builder()
                .status("CLOSED")
                .orderId("1234")
                .build();

        //WHEN
        target.updateStatusOrder(orderUpdate);

        //THEN
        verify(updateStatusOrderUseCase, times(1)).updateStatus(anyString(), anyString());
    }
}