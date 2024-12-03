package com.deliverynow.order.infrastructure.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "order")
public class OrderEntity {

    private String orderId;
    private String sessionId;
    private LocalDateTime createDate;
    private String statusOrder;
    private ClientEntity customer;
    private List<ItemEntity> items;
    private OrderDetailEntity orderDetail;
    private TotalEntity total;
}
