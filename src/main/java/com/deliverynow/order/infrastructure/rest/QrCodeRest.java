package com.deliverynow.order.infrastructure.rest;

import com.deliverynow.order.infrastructure.rest.request.QrCodeRequest;
import com.deliverynow.order.infrastructure.rest.response.QrCodeResponse;
import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/instore/orders/qr/seller/collectors")
@RegisterRestClient(baseUri = "https://api.mercadopago.com")
public interface QrCodeRest {

    @POST
    @Path("/{user_id}/pos/{external_pos_id}/qrs")
    QrCodeResponse generatedQrCode(@HeaderParam("Authorization") String authorization,
                                   @PathParam("user_id") String userId,
                                   @PathParam("external_pos_id") String externalPosId,
                                   QrCodeRequest paymentRequest);

    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        String respStr = response.readEntity(String.class);
        if (response.getStatus() == 500) {
            return new RuntimeException("The remote service responded with HTTP 500");
        }
        return null;
    }
}
