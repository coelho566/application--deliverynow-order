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
public class MerchantPaymentResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("transaction_amount")
    private Integer transactionAmount;

    @JsonProperty("total_paid_amount")
    private Integer totalPaidAmount;

    @JsonProperty("shipping_cost")
    private Integer shippingCost;

    @JsonProperty("currency_id")
    private String currencyId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("status_detail")
    private String statusDetail;

    @JsonProperty("operation_type")
    private String operationType;

    @JsonProperty("date_approved")
    private String dateApproved;

    @JsonProperty("date_created")
    private String dateCreated;

    @JsonProperty("last_modified")
    private String lastModified;

    @JsonProperty("amount_refunded")
    private Integer amountRefunded;
}
