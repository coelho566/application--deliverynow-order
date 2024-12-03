package com.deliverynow.order.infrastructure.rest;

import com.deliverynow.order.infrastructure.rest.response.MerchantOrderResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/merchant_orders")
@RegisterRestClient(baseUri = "https://api.mercadolibre.com")
public interface MerchantOrderRest {

    @GET
    @Path("/{code_payment}")
    MerchantOrderResponse generatedQrCode(@HeaderParam("Authorization") String authorization,
                                          @PathParam("code_payment") String codePayment);

}
