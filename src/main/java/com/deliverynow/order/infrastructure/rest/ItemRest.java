package com.deliverynow.order.infrastructure.rest;

import com.deliverynow.order.infrastructure.rest.response.BaseResponse;
import com.deliverynow.order.infrastructure.rest.response.CustomerResponse;
import com.deliverynow.order.infrastructure.rest.response.ItemResponse;
import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/application--deliverynow-product")
@RegisterRestClient(baseUri = "http://localhost:8082")
public interface ItemRest {

    @GET
    @Path("/item/{sessionId}")
    BaseResponse<List<ItemResponse>> getItemBySession(@PathParam("sessionId") String sessionId);

}
