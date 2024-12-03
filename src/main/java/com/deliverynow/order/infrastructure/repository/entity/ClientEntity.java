package com.deliverynow.order.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    private String name;
    private String phone;
    private String email;
    private String document;
    private AddressEntity address;
}
