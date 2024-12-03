package com.deliverynow.order.adapters.gateway.rest;

import com.deliverynow.order.infrastructure.config.OrderProperties;
import com.deliverynow.order.infrastructure.rest.MerchantOrderRest;
import com.deliverynow.order.infrastructure.rest.response.MerchantOrderResponse;
import com.deliverynow.order.mock.OrderFactory;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
class ProcessPaymentMpGatewayTest {

    @InjectMocks
    ProcessPaymentMpGateway target;
    @Mock
    MerchantOrderRest merchantOrderRest;
    @Mock
    OrderProperties orderProperties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustProcessPaymentTest() {
        //GIVEN
        var orderId = "123456";
        var merchant = OrderFactory.createMerchantOrderResponse("APROVED");

        //WHEN
        when(merchantOrderRest.generatedQrCode(anyString(), anyString())).thenReturn(merchant);
        when(orderProperties.authorization()).thenReturn("token");
        var merchantOrderResponse = target.processPayment(orderId);

        //THEN
        assertNotNull(merchantOrderResponse);
    }
}