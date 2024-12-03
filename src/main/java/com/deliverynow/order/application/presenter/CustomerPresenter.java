package com.deliverynow.order.application.presenter;

import com.deliverynow.order.domain.entity.CustomerOrder;
import com.deliverynow.order.infrastructure.rest.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "jakarta", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerPresenter {

    CustomerOrder toDomain(CustomerResponse customerResponse);

}
