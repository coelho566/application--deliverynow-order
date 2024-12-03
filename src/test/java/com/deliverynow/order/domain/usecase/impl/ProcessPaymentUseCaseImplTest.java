package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.adapters.gateway.repository.PaymentRepositoryGateway;
import com.deliverynow.order.application.exception.PaymentException;
import com.deliverynow.order.application.gateway.ProcessPaymentGateway;
import com.deliverynow.order.application.presenter.PaymentPresenter;
import com.deliverynow.order.domain.usecase.UpdateStatusOrderUseCase;
import com.deliverynow.order.infrastructure.rest.response.MerchantOrderResponse;
import com.deliverynow.order.infrastructure.rest.response.MerchantPaymentResponse;
import com.deliverynow.order.mock.OrderFactory;
import com.github.benmanes.caffeine.cache.Cache;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@QuarkusTest
class ProcessPaymentUseCaseImplTest {

    @InjectMocks
    ProcessPaymentUseCaseImpl target;
    @Mock
    ProcessPaymentGateway processPaymentGateway;
    @Mock
    UpdateStatusOrderUseCase updateStatusOrderUseCase;
    @Mock
    PaymentRepositoryGateway paymentRepositoryGateway;
    @Spy
    PaymentPresenter paymentPresenter = Mappers.getMapper(PaymentPresenter.class);
    @Spy
    Cache<String, Boolean> processedIdsCache;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void mustExecuteProcessPayment() {
        //GIVEN
        var orderId = "123456";
        var merchantPaymentResponse = OrderFactory.createMerchantOrderResponse("CLOSED");

        //WHEN
        when(processPaymentGateway.processPayment(anyString())).thenReturn(merchantPaymentResponse);
        target.execute(orderId);

        //THEN
        verify(paymentRepositoryGateway, times(1)).insertPayment(any());
        verify(updateStatusOrderUseCase, times(1)).updateStatus(anyString(), anyString());
    }

    @Test
    void mustErrorExecuteProcessPayment() {
        //GIVEN
        var orderId = "123456";

        //WHEN
        when(processPaymentGateway.processPayment(anyString())).thenThrow(RuntimeException.class);
        var paymentException = assertThrows(PaymentException.class, () -> target.execute(orderId));

        //THEN
        assertEquals("Error processing payment", paymentException.getMessage());
    }

}