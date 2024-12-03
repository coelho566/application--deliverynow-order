package com.deliverynow.order.infrastructure.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeItemRequest {

    @JsonProperty("sku_number")
    private String skuNumber;
    @JsonProperty("category")
    private String category;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("unit_price")
    private Double unitPrice;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("unit_measure")
    private String unitMeasure;
    @JsonProperty("total_amount")
    private Double totalAmount;
}
