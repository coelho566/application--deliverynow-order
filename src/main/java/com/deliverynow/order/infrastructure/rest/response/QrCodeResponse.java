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
public class QrCodeResponse {

    @JsonProperty("in_store_order_id")
    private String inStoreOrderId;
    @JsonProperty("qr_data")
    private String qrData;
}
