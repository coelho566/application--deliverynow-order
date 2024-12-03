package com.deliverynow.order.infrastructure.rest.response;

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
public class MerchantOrderResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("external_reference")
    private String externalReference;
    @JsonProperty("preference_id")
    private String preferenceId;
    @JsonProperty("payments")
    private List<MerchantPaymentResponse> merchantPayments;
    @JsonProperty("marketplace")
    private String marketplace;
    @JsonProperty("notification_url")
    private String notificationUrl;
    @JsonProperty("date_created")
    private String dateCreated;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("sponsor_id")
    private String sponsorId;
    @JsonProperty("shipping_cost")
    private Double shippingCost;
    @JsonProperty("total_amount")
    private Double totalAmount;
    @JsonProperty("site_id")
    private String siteId;
    @JsonProperty("paid_amount")
    private Double paidAmount;
    @JsonProperty("refunded_amount")
    private Double refundedAmount;
    @JsonProperty("items")
    private List<MerchantItemResponse> items;
    @JsonProperty("cancelled")
    private boolean cancelled;
    @JsonProperty("additional_info")
    private String additionalInfo;
    @JsonProperty("application_id")
    private String applicationId;
    @JsonProperty("is_test")
    private boolean isTest;
    @JsonProperty("order_status")
    private String orderStatus;
    @JsonProperty("client_id")
    private String clientId;

}
