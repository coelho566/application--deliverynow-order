package com.deliverynow.order.application.presenter;

import com.deliverynow.order.adapters.controller.response.OrderResponse;
import com.deliverynow.order.domain.entity.CustomerOrder;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.infrastructure.repository.entity.OrderEntity;
//import com.deliverynow.user.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "jakarta", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderPresenter {

    OrderResponse domainToResponse(Order order);
    //Todo - ajustar
//    CustomerOrder customerToCustomerOrder(Customer customer);
    OrderEntity domainToEntity(Order order);
    Order entityToDomain(OrderEntity order);
}
