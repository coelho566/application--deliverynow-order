package com.deliverynow.order.application.controller;

import com.deliverynow.order.adapters.controller.response.PaymentResponse;
import com.deliverynow.order.adapters.controller.response.QrCodePaymentResponse;
import com.deliverynow.order.application.presenter.PaymentPresenter;
import com.deliverynow.order.domain.usecase.GenerateQrCodeUseCase;
import com.deliverynow.order.domain.usecase.PaymentStatusUseCase;
import com.deliverynow.order.domain.usecase.ProcessPaymentUseCase;
import com.deliverynow.order.domain.usecase.ValidateWebhookUseCase;
import com.deliverynow.order.mock.PaymentFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@QuarkusTest
class PaymentControllerImplTest {

    @InjectMocks
    PaymentControllerImpl target;
    @Mock
    GenerateQrCodeUseCase qrCodeUseCase;
    @Mock
    ProcessPaymentUseCase processPaymentUseCase;
    @Mock
    PaymentStatusUseCase paymentStatusUseCase;
    @Mock
    ValidateWebhookUseCase validateWebhookUseCase;
    @Spy
    PaymentPresenter paymentPresenter = Mappers.getMapper(PaymentPresenter.class);


    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustGetPaymentTest() {
        //GIVEN
        var id = "123";
        var paymentDomain = PaymentFactory.createMockPayment();


        //WHEN
        when(paymentStatusUseCase.getPaymentByStatus(anyString())).thenReturn(paymentDomain);
        var payment = target.getPayment(id);

        //THEN
        assertNotNull(payment);
    }

    @Test
    void mustGetQrCodeOrderTest() {
        //GIVEN
        var id = "123";
        var qrCodePaymentResponse = QrCodePaymentResponse.builder()
                .dateExpiration("12/10/2025")
                .qrCode("xxx")
                .inStoreOrderId("asd")
                .build();

        //WHEN
        when(qrCodeUseCase.getQrCode(anyString())).thenReturn(qrCodePaymentResponse);
        var qrCodeOrder = target.getQrCodeOrder(id);

        //THEN
        assertNotNull(qrCodeOrder);
    }

    @Test
    void mustProcessTest() {
        //GIVEN
        var payload =  "payload";

        //WHEN
        when(validateWebhookUseCase.getPaymentIdOrder(anyString())).thenReturn(Optional.of("123"));
        target.process(payload);

        //THEN
        verify(processPaymentUseCase, times(1)).execute(anyString());
    }
}