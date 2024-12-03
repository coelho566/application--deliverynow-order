package com.deliverynow.order.adapters.gateway.rest;

import com.deliverynow.order.application.exception.OrderException;
import com.deliverynow.order.application.gateway.ItemGateway;
import com.deliverynow.order.application.presenter.CustomerPresenter;
import com.deliverynow.order.application.presenter.ItemOrderPresenter;
import com.deliverynow.order.domain.entity.ItemOrder;
import com.deliverynow.order.infrastructure.rest.CustomerRest;
import com.deliverynow.order.infrastructure.rest.ItemRest;
import com.deliverynow.order.infrastructure.rest.response.BaseResponse;
import com.deliverynow.order.infrastructure.rest.response.ItemResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Slf4j
@ApplicationScoped
public class ItemRestGateway implements ItemGateway {


    @Inject
    @RestClient
    ItemRest itemRest;

    @Inject
    ItemOrderPresenter itemOrderPresenter;

    @Override
    public List<ItemOrder> getItemsBySession(String sessionId) {
        var itemBySession = itemRest.getItemBySession(sessionId);

        if(!itemBySession.getData().isEmpty()){
            return itemBySession.getData()
                    .stream()
                    .map(itemOrderPresenter::itemToItemOrder)
                    .toList();
        }else{
            throw new OrderException(String.format("Order not found with sessioId %s", sessionId));
        }
    }
}
