package com.deliverynow.order.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    private String itemId;
    private String productId;
    private String name;
    private String category;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
}
