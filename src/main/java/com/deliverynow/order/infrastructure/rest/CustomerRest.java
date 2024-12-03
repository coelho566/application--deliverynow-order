package com.deliverynow.order.infrastructure.rest;

import com.deliverynow.order.infrastructure.rest.response.BaseResponse;
import com.deliverynow.order.infrastructure.rest.response.CustomerResponse;
import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/application--deliverynow-user")
@RegisterRestClient(baseUri = "http://localhost:8080")
public interface CustomerRest {

    @GET
    @Path("/customer/session/{sessionId}")
    BaseResponse<CustomerResponse> generatedQrCode(@PathParam("sessionId") String sessionId);

    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        String respStr = response.readEntity(String.class);
        if (response.getStatus() == 500) {
            return new RuntimeException("The remote service responded with HTTP 500");
        }
        return null;
    }
}
