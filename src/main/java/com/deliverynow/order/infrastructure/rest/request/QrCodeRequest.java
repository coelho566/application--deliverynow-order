package com.deliverynow.order.infrastructure.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeRequest {
    @JsonProperty("description")
    private String description;
    @JsonProperty("external_reference")
    private String externalReference;
    @JsonProperty("expiration_date")
    private String expirationDate;
    private List<QrCodeItemRequest> items;
    @JsonProperty("notification_url")
    private String notificationUrl;
    @JsonProperty("title")
    private String title;
    @JsonProperty("total_amount")
    private Double totalAmount;
}
