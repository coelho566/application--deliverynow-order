package com.deliverynow.order.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {
    private String name;
    private String phone;
    private String email;
    private String document;
    private Address address;
}
