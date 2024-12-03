package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.infrastructure.config.OrderProperties;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@QuarkusTest
class ValidateWebhookUseCaseImplTest {

    @InjectMocks
    ValidateWebhookUseCaseImpl target;

    @Mock
    OrderProperties orderProperties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void mustGetPaymentIdOrderTest() {
        //GIVEN
        var payload = "{\n  \"resource\": \"https://api.mercadolibre.com/merchant_orders/25385406621\",\n  \"topic\": \"merchant_order\"\n}";

        //WHEN
        when(orderProperties.url()).thenReturn("https://api.mercadolibre.com");
        var paymentIdOrder = target.getPaymentIdOrder(payload);

        //THEN
        assertTrue(paymentIdOrder.isPresent());
        assertEquals("25385406621", paymentIdOrder.get());
    }

    @Test
    void mustGetPaymentIdOrderEmptyTest() {
        //GIVEN
        var payload = "{\n  \"resource\": \"94394235754\",\n  \"topic\": \"payment\"\n}";

        //WHEN
        when(orderProperties.url()).thenReturn("https://api.mercadolibre.com");
        var paymentIdOrder = target.getPaymentIdOrder(payload);

        //THEN
        assertTrue(paymentIdOrder.isEmpty());
    }

    @Test
    void mustGetPaymentIdOrderErrorJsonTest() {
        //GIVEN
        var payload = "{\n  \"resource\": \"94394235754\",\n  : \"payment\"\n}";

        //WHEN
        when(orderProperties.url()).thenReturn("https://api.mercadolibre.com");
        var paymentIdOrder = target.getPaymentIdOrder(payload);

        //THEN
        assertTrue(paymentIdOrder.isEmpty());
    }
}