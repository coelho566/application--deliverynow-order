package com.deliverynow.order.adapters.gateway.rest;

import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.infrastructure.config.OrderProperties;
import com.deliverynow.order.infrastructure.rest.QrCodeRest;
import com.deliverynow.order.infrastructure.rest.response.QrCodeResponse;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
class QrCodePaymentRestGatewayTest {

    @InjectMocks
    QrCodePaymentRestGateway target;
    @Mock
    QrCodeRest paymentRest;
    @Mock
    OrderProperties orderProperties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustProcessPaymentTest() {
        //GIVEN
        var order = OrderFactory.createOrderDomain(OrderStatusEnum.RECEBIDO);
        var qrCode = QrCodeResponse.builder()
                .inStoreOrderId("123")
                .qrData("xxxx")
                .build();

        //WHEN
        when(paymentRest.generatedQrCode(anyString(), anyString(), anyString(), any())).thenReturn(qrCode);
        var qrCodeResponse = target.processPayment(order);

        //THEN
        assertNull(qrCodeResponse);
    }
}