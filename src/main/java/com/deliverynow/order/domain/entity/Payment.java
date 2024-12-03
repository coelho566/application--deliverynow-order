package com.deliverynow.order.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private String paymentId;
    private String orderId;
    private String transactionAmount;
    private String totalPaidAmount;
    private String shippingCost;
    private String currencyId;
    private String status;
    private String statusDetail;
    private String operationType;
    private String dateApproved;
    private String dateCreated;
    private String lastModified;
    private String amountRefunded;
    private String method;
}
