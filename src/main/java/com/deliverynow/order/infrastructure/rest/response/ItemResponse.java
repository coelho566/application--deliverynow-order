package com.deliverynow.order.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    private String itemId;
    private String productId;
    private String customerId;
    private String name;
    private String description;
    private String category;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;

}
