package com.deliverynow.order.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    private String postalCode;
    private String street;
    private String state;
    private String city;
    private String numberAddress;
}
