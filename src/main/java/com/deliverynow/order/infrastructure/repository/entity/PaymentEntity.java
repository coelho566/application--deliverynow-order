package com.deliverynow.order.infrastructure.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "payment")
public class PaymentEntity {

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
