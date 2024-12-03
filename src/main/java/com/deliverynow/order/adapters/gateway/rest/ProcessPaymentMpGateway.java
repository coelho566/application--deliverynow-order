package com.deliverynow.order.adapters.gateway.rest;

import com.deliverynow.order.application.gateway.ProcessPaymentGateway;
import com.deliverynow.order.infrastructure.config.OrderProperties;
import com.deliverynow.order.infrastructure.rest.MerchantOrderRest;
import com.deliverynow.order.infrastructure.rest.response.MerchantOrderResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ProcessPaymentMpGateway implements ProcessPaymentGateway {

    @Inject
    @RestClient
    MerchantOrderRest merchantOrderRest;
    @Inject
    OrderProperties orderProperties;


    @Override
    public MerchantOrderResponse processPayment(String orderId) {
        var auth = String.format("Bearer %s", orderProperties.authorization());
        return merchantOrderRest.generatedQrCode(auth, orderId);
    }
}
