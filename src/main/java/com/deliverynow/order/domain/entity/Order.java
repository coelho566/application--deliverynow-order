package com.deliverynow.order.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Builder
@Getter
@AllArgsConstructor
public class Order {
    private String orderId;
    @Setter
    private String sessionId;
    private LocalDateTime createDate;
    @Setter
    private OrderStatusEnum statusOrder;
    private CustomerOrder customer;
    private List<ItemOrder> items;
    private OrderDetail orderDetail;
    private Total total;

    public void calculatedTotal() {

        var subTotal = this.items.stream()
                .filter(itemOrder -> itemOrder.getTotalPrice() != null)
                .mapToDouble(ItemOrder::getTotalPrice).sum();
        this.total = Total.builder()
                .subtotal(subTotal)
                .discount(0.0)
                .taxes(0.0)
                .finalTotal(subTotal)
                .build();
    }

    public void setCreateDate() {
        this.createDate = LocalDateTime.now();
    }

    public void setOrderId() {
        var random = new Random();
        int min = 10000000;
        int max = 99999999;

        int randomNumber = random.nextInt((max - min) + 1) + min;
        this.orderId = String.valueOf(randomNumber);
    }

    public static final Comparator<Order> COMPARATOR = Comparator
            .comparing(Order::getStatusOrder, new StatusOrderComparator())
            .thenComparing(Order::getCreateDate);

    private static class StatusOrderComparator implements Comparator<OrderStatusEnum> {
        @Override
        public int compare(OrderStatusEnum status1, OrderStatusEnum status2) {
            return Integer.compare(getOrder(status1), getOrder(status2));
        }

        private int getOrder(OrderStatusEnum status) {
            return switch (status) {
                case OrderStatusEnum.PRONTO -> 1;
                case OrderStatusEnum.EM_PREPARACAO -> 2;
                case OrderStatusEnum.FINALIZADO -> 3;
                default -> Integer.MAX_VALUE; // Para outros status não especificados
            };
        }
    }
}
