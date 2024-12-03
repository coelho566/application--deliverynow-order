package com.deliverynow.order.adapters.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderResponse {

    @Schema(description = "Client name", example = "Mario Word")
    private String name;
    @Schema(description = "Client phone number", example = "1234567890")
    private String phone;
    @Schema(description = "Client email", example = "mario.word@example.com")
    private String email;
    @Schema(description = "Client document", example = "08197588479")
    private String document;
    private AddressResponse address;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressResponse{
        private String postalCode;
        private String street;
        private String state;
        private String city;
        private String numberAddress;
    }

}
