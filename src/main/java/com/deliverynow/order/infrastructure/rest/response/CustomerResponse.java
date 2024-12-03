package com.deliverynow.order.infrastructure.rest.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private String name;
    private String phone;
    private String email;
    private String document;
    private AddressResponse address;
}
