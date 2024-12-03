package com.deliverynow.order.adapters.gateway.repository;

import com.deliverynow.order.application.presenter.PaymentPresenter;
import com.deliverynow.order.infrastructure.repository.PaymentRepository;
import com.deliverynow.order.infrastructure.repository.entity.PaymentEntity;
import com.deliverynow.order.mock.PaymentFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@QuarkusTest
class PaymentRepositoryGatewayTest {

    @InjectMocks
    PaymentRepositoryGateway target;
    @Mock
    PaymentRepository paymentRepository;
    @Spy
    PaymentPresenter paymentPresenter = Mappers.getMapper(PaymentPresenter.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void mustInsertPaymentTest() {
        //GIVEN
        var payment = PaymentFactory.createMockPayment();

        //WHEN
        target.insertPayment(payment);

        //GIVEN
        verify(paymentRepository, times(1)).persist(Mockito.any(PaymentEntity.class));
    }

    @Test
    void mustGetByOrderIdTest() {
        //GIVEN
        var orderId = "123456";
        var payment = PaymentFactory.createPaymentEntity();

        //WHEN
        when(paymentRepository.findByOrderId(anyString())).thenReturn(Optional.of(payment));
        var paymentOptional = target.getByOrderId(orderId);

        //THEN
        assertTrue(paymentOptional.isPresent());
    }
}