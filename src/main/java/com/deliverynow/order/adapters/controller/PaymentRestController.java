package com.deliverynow.order.adapters.controller;

import com.deliverynow.order.adapters.controller.response.BaseResponse;
import com.deliverynow.order.adapters.controller.response.PaymentResponse;
import com.deliverynow.order.adapters.controller.response.QrCodePaymentResponse;
import com.deliverynow.order.application.controller.PaymentController;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/payment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Payment controller", description = "Payment operations")
public class PaymentRestController {

    PaymentController paymentController;

    public PaymentRestController(PaymentController paymentController) {
        this.paymentController = paymentController;
    }

    @GET
    @Path("{orderId}")
    @Operation(summary = "Consultar status pagamento")
    public RestResponse<BaseResponse<PaymentResponse>> getpayment(@PathParam("orderId") String id) {
        var paymentByStatus = paymentController.getPayment(id);
        return RestResponse.ok(new BaseResponse<>(paymentByStatus));
    }

    @GET
    @Path("qrcode/{orderId}")
    @Operation(summary = "Gerar codigo QrCode")
    public RestResponse<BaseResponse<QrCodePaymentResponse>> getResumeOrder(@PathParam("orderId") String id) {
        var qrCode = paymentController.getQrCodeOrder(id);
        return RestResponse.ok(new BaseResponse<>(qrCode));
    }

    @POST
    @Path("webhook")
    @Operation(summary = "Gerar codigo QrCode")
    public RestResponse<?> paymentWebhook(String payload) {
        paymentController.process(payload);
        return RestResponse.ok();
    }
}
