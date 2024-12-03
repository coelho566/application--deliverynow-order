package com.deliverynow.order.application.gateway;

import com.deliverynow.order.domain.entity.CustomerOrder;

public interface CustomerGateway {

    CustomerOrder getCustomerById(String sessionId);
}
