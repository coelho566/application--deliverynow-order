package com.deliverynow.order.adapters.gateway.rest;

import com.deliverynow.order.application.gateway.CustomerGateway;
import com.deliverynow.order.application.presenter.CustomerPresenter;
import com.deliverynow.order.domain.entity.CustomerOrder;
import com.deliverynow.order.infrastructure.rest.CustomerRest;
import com.deliverynow.order.infrastructure.rest.response.CustomerResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@Slf4j
@ApplicationScoped
public class CustomerRestGateway implements CustomerGateway {


    @Inject
    @RestClient
    CustomerRest customerRest;

    @Inject
    CustomerPresenter customerPresenter;

    @Override
    public CustomerOrder getCustomerById(String sessionId) {

        var customerResponse = Optional.ofNullable(customerRest.generatedQrCode(sessionId));
        return customerResponse
                .map(customer -> customerPresenter.toDomain(customer.getData()))
                .orElse(null);
    }
}
