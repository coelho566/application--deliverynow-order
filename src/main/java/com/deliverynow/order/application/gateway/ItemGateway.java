package com.deliverynow.order.application.gateway;

import com.deliverynow.order.domain.entity.ItemOrder;

import java.util.List;

public interface ItemGateway {

    List<ItemOrder> getItemsBySession(String sessionId);
}
