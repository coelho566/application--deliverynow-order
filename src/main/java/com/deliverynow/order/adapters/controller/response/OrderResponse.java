package com.deliverynow.order.adapters.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String orderId;
    private String createDate;
    private String statusOrder;
    private CustomerOrderResponse customer;
    private List<ItemOrderResponse> items;
    private TotalResponse total;
}
