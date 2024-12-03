package com.deliverynow.order.adapters.controller;

import com.deliverynow.order.adapters.controller.request.OrderRequest;
import com.deliverynow.order.adapters.controller.request.OrderUpdateStatusRequest;
import com.deliverynow.order.adapters.controller.response.BaseResponse;
import com.deliverynow.order.adapters.controller.response.CheckoutResponse;
import com.deliverynow.order.adapters.controller.response.OrderResponse;
import com.deliverynow.order.application.controller.OrderController;
import com.deliverynow.order.mock.OrderFactory;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class OrderRestControllerTest {

    @InjectMocks
    OrderRestController target;

    @Mock
    OrderController orderController;

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

        var checkoutResponse = CheckoutResponse.builder()
                .orderId("123")
                .build();
        //WHEN
        when(orderController.orderCheckout(any())).thenReturn(checkoutResponse);
        var baseResponseRestResponse = target.orderCheckout(orderRequest);

        //THEN
        assertNotNull(baseResponseRestResponse);
    }

    @Test
    void mustGetAllOrders() {
        //GIVEN
        var orderResponse = OrderFactory.createOrderResponse("CREATED");

        //WHEN
        when(orderController.getAllOrders()).thenReturn(List.of(orderResponse));
        var allOrders = target.getAllOrders();

        //THEN
        assertNotNull(allOrders);
        assertEquals(200, allOrders.getStatus());
    }

    @Test
    void mustGetResumeOrderTest() {
        //GIVEN
        var sessionId = "123456";
        var orderResponse = OrderFactory.createOrderResponse("CREATED");

        //WHEN
        when(orderController.getResumeOrder(Mockito.anyString())).thenReturn(orderResponse);
        var resumeOrder = target.getResumeOrder(sessionId);

        //THEN
        assertNotNull(resumeOrder);
        assertEquals(200, resumeOrder.getStatus());
    }

    @Test
    void mustUpdateOrderTest() {
        //GIVEN
        OrderUpdateStatusRequest orderRequest = OrderUpdateStatusRequest.builder()
                .status("CLOSED")
                .orderId("123456")
                .build();

        //WHEN
        target.updateOrder(orderRequest);

        //THEN
        verify(orderController, times(1)).updateStatusOrder(any());
    }
}