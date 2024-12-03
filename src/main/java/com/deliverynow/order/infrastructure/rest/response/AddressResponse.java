package com.deliverynow.order.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
        private String postalCode;
        private String street;
        private String state;
        private String city;
        private String numberAddress;
}
