package com.deliverynow.order.adapters.controller;

import com.deliverynow.order.adapters.controller.response.BaseResponse;
import com.deliverynow.order.adapters.controller.response.PaymentResponse;
import com.deliverynow.order.adapters.controller.response.QrCodePaymentResponse;
import com.deliverynow.order.application.controller.PaymentController;
import com.deliverynow.order.mock.PaymentFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.jboss.resteasy.reactive.RestResponse;
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
class PaymentRestControllerTest {

    @InjectMocks
    PaymentRestController target;

    @Mock
    PaymentController paymentController;


    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void mustGetpaymentTest() {
        //GIVEN
        var orderId = "123";
        var payment = PaymentFactory.createPaymentResponse();

        //WHEN
        when(paymentController.getPayment(anyString())).thenReturn(payment);
        var getPayment = target.getpayment(orderId);

        //THEN
        assertNotNull(getPayment);
        assertEquals(200, getPayment.getStatus());
    }

    @Test
    void mustGetResumeOrderTest() {
        //GIVEN
        var orderId = "123";
        var qrCodePaymentResponse = QrCodePaymentResponse.builder()
                .dateExpiration("12/10/2025")
                .qrCode("xxx")
                .inStoreOrderId("asd")
                .build();

        //WHEN
        when(paymentController.getQrCodeOrder(Mockito.any())).thenReturn(qrCodePaymentResponse);
        var resumeOrder = target.getResumeOrder(orderId);

        //THEN
        assertNotNull(resumeOrder);
        assertEquals(200, resumeOrder.getStatus());
    }

    @Test
    void mustPaymentWebhookTest() {
        //GIVEN
        var payload = "teste";
        var paymentResponse = PaymentFactory.createPaymentResponse();

        //WHEN
        when(paymentController.getPayment(anyString())).thenReturn(paymentResponse);
        var restResponse = target.paymentWebhook(payload);

        //THEN
        assertEquals(200, restResponse.getStatus());

    }
}