package com.deliverynow.order.infrastructure.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantItemResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("currency_id")
    private String currencyId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("picture_url")
    private String pictureUrl;

    @JsonProperty("title")
    private String title;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("unit_price")
    private Double unitPrice;
}
