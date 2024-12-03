package com.deliverynow.order.application.presenter;

import com.deliverynow.order.adapters.controller.response.PaymentResponse;
import com.deliverynow.order.domain.entity.Payment;
import com.deliverynow.order.infrastructure.repository.entity.PaymentEntity;
import com.deliverynow.order.infrastructure.rest.response.MerchantPaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "jakarta", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentPresenter {

    PaymentEntity domainToEntity(Payment payment);
    Payment entityToDomain(PaymentEntity payment);
    PaymentResponse domainToResponse(Payment payment);

    @Mapping(target = "method", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "paymentId", ignore = true)
    Payment paymentMerchantToDomain(MerchantPaymentResponse paymentResponse);
}
