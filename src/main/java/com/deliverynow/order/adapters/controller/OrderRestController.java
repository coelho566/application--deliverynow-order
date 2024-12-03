package com.deliverynow.order.adapters.controller;


import com.deliverynow.order.adapters.controller.request.OrderRequest;
import com.deliverynow.order.adapters.controller.request.OrderUpdateStatusRequest;
import com.deliverynow.order.adapters.controller.response.BaseResponse;
import com.deliverynow.order.adapters.controller.response.CheckoutResponse;
import com.deliverynow.order.adapters.controller.response.OrderResponse;
import com.deliverynow.order.application.controller.OrderController;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Order controller", description = "Order operations")
public class OrderRestController {

    OrderController orderController;

    public OrderRestController(OrderController orderController) {
        this.orderController = orderController;
    }

    @POST
    @Path("/checkout")
    @Operation(summary = "Fazer checkout do pedido")
    public RestResponse<BaseResponse<CheckoutResponse>> orderCheckout(@Valid OrderRequest orderRequest) {
        var checkoutOrder = orderController.orderCheckout(orderRequest);
        BaseResponse<CheckoutResponse> objectBaseResponse = new BaseResponse<>();
        objectBaseResponse.setData(checkoutOrder);
        return RestResponse.ok(objectBaseResponse);
    }

    @GET
    @Operation(summary = "Lista todos os pedidos")
    public RestResponse<BaseResponse<List<OrderResponse>>> getAllOrders() {
        var orders = orderController.getAllOrders();
        return RestResponse.ok(new BaseResponse<>(orders));
    }

    @GET
    @Path("resume/{customerId}")
    @Operation(summary = "Resumir pedido")
    public RestResponse<BaseResponse<OrderResponse>> getResumeOrder(@PathParam("customerId") String id) {
        var resumeOrder = orderController.getResumeOrder(id);
        return RestResponse.ok(new BaseResponse<>(resumeOrder));
    }

    @PUT
    @Path("/update/status")
    @Operation(summary = "Atualizar status pedido")
    public RestResponse<Void> updateOrder(@Valid OrderUpdateStatusRequest orderRequest) {
        orderController.updateStatusOrder(orderRequest);
        return RestResponse.ok();
    }
}

