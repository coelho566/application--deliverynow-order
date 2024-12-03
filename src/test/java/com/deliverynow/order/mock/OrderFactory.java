package com.deliverynow.order.mock;

import com.deliverynow.order.adapters.controller.response.CustomerOrderResponse;
import com.deliverynow.order.adapters.controller.response.ItemOrderResponse;
import com.deliverynow.order.adapters.controller.response.OrderResponse;
import com.deliverynow.order.adapters.controller.response.TotalResponse;
import com.deliverynow.order.domain.entity.*;
import com.deliverynow.order.infrastructure.repository.entity.*;
import com.deliverynow.order.infrastructure.rest.response.MerchantItemResponse;
import com.deliverynow.order.infrastructure.rest.response.MerchantOrderResponse;
import com.deliverynow.order.infrastructure.rest.response.MerchantPaymentResponse;

import java.util.List;

public class OrderFactory {

    public static Order createOrderDomain(OrderStatusEnum statusEnum){
        var address = Address.builder()
                .street("Rua Oziel Fonseca de Souza")
                .numberAddress("1457")
                .city("Curitiba")
                .state("PR")
                .postalCode("81265-228")
                .build();

        var customer = CustomerOrder.builder()
                .name("Mario Word")
                .document("08197588474")
                .email("mario.word@example.com")
                .phone("1234567890")
                .address(address)
                .build();

        var item1 = ItemOrder.builder()
                .itemId("673de354257c173d1f556e7b")
                .productId("66fc89e350139ea5cbaccf9d")
                .name("Cheeseburger")
                .category("Lanche")
                .description("Um delicioso cheeseburger com carne, queijo cheddar, alface e tomate.")
                .quantity(2)
                .unitPrice(15.99)
                .totalPrice(31.98)
                .build();

        var item2 = ItemOrder.builder()
                .itemId("673de354257c173d1f556e7c")
                .productId("66fc89e350139ea5cbaccfab")
                .name("Refrigerante")
                .category("Bebida")
                .description("Refrigerante gelado de diversos sabores.")
                .quantity(2)
                .unitPrice(2.99)
                .totalPrice(5.98)
                .build();

        var total = Total.builder()
                .subtotal(37.96)
                .finalTotal(37.96)
                .discount(0.0)
                .taxes(0.0)
                .build();

        return Order.builder()
                .orderId("123456")
                .customer(customer)
                .statusOrder(statusEnum)
                .items(List.of(item1, item2))
                .total(total)
                .build();
    }

    public static MerchantOrderResponse createMerchantOrderResponse(String status){


        var item = MerchantItemResponse.builder()
                .id("")
                .categoryId("marketplace")
                .currencyId("BRL")
                .description("This is the Point Mini")
                .pictureUrl(null)
                .title("Point Mini")
                .quantity(2)
                .unitPrice(100.0)
                .build();

        var payment = MerchantPaymentResponse.builder()
                .id(94394235754L)
                .transactionAmount(200)
                .totalPaidAmount(200)
                .shippingCost(0)
                .currencyId("BRL")
                .status("approved")
                .statusDetail("accredited")
                .operationType("regular_payment")
                .build();

        return MerchantOrderResponse.builder()
                .id(25384475489L)
                .status(status)
                .externalReference("reference_12345")
                .preferenceId("1893050778-285a49cc-3472-4a56-9d69-423a32380b9a")
                .marketplace("NONE")
                .merchantPayments(List.of(payment))
                .notificationUrl("https://webhook.site/d9dc4379-40da-427e-a0f8-aae66544f72d")
                .dateCreated("2024-11-25T13:27:56.873-04:00")
                .lastUpdated("2024-11-25T13:27:56.873-04:00")
                .sponsorId(null)
                .shippingCost(0.0)
                .totalAmount(200.0)
                .siteId("MLB")
                .paidAmount(0.0)
                .refundedAmount(0.0)
                .items(List.of(item))
                .cancelled(false)
                .additionalInfo("")
                .applicationId(null)
                .isTest(true)
                .orderStatus("payment_required")
                .clientId("7053979908591335")
                .build();
    }

    public static OrderEntity createOrderEntity(String status){
        var address = AddressEntity.builder()
                .street("Rua Oziel Fonseca de Souza")
                .numberAddress("1457")
                .city("Curitiba")
                .state("PR")
                .postalCode("81265-228")
                .build();

        var customer = ClientEntity.builder()
                .name("Mario Word")
                .document("08197588474")
                .email("mario.word@example.com")
                .phone("1234567890")
                .address(address)
                .build();

        var item1 = ItemEntity.builder()
                .itemId("673de354257c173d1f556e7b")
                .productId("66fc89e350139ea5cbaccf9d")
                .name("Cheeseburger")
                .category("Lanche")
                .description("Um delicioso cheeseburger com carne, queijo cheddar, alface e tomate.")
                .quantity(2)
                .unitPrice(15.99)
                .totalPrice(31.98)
                .build();

        var item2 = ItemEntity.builder()
                .itemId("673de354257c173d1f556e7c")
                .productId("66fc89e350139ea5cbaccfab")
                .name("Refrigerante")
                .category("Bebida")
                .description("Refrigerante gelado de diversos sabores.")
                .quantity(2)
                .unitPrice(2.99)
                .totalPrice(5.98)
                .build();

        var total = TotalEntity.builder()
                .subtotal(37.96)
                .finalTotal(37.96)
                .discount(0.0)
                .taxes(0.0)
                .build();

        return OrderEntity.builder()
                .customer(customer)
                .statusOrder(status)
                .items(List.of(item1, item2))
                .total(total)
                .build();
    }

    public static OrderResponse createOrderResponse(String statusEnum){
        var address = CustomerOrderResponse.AddressResponse.builder()
                .street("Rua Oziel Fonseca de Souza")
                .numberAddress("1457")
                .city("Curitiba")
                .state("PR")
                .postalCode("81265-228")
                .build();

        var customer = CustomerOrderResponse.builder()
                .name("Mario Word")
                .document("08197588474")
                .email("mario.word@example.com")
                .phone("1234567890")
                .address(address)
                .build();

        var item1 = ItemOrderResponse.builder()
                .itemId("673de354257c173d1f556e7b")
                .productId("66fc89e350139ea5cbaccf9d")
                .name("Cheeseburger")
                .category("Lanche")
                .description("Um delicioso cheeseburger com carne, queijo cheddar, alface e tomate.")
                .quantity(2)
                .unitPrice(15.99)
                .totalPrice(31.98)
                .build();

        var item2 = ItemOrderResponse.builder()
                .itemId("673de354257c173d1f556e7c")
                .productId("66fc89e350139ea5cbaccfab")
                .name("Refrigerante")
                .category("Bebida")
                .description("Refrigerante gelado de diversos sabores.")
                .quantity(2)
                .unitPrice(2.99)
                .totalPrice(5.98)
                .build();

        var total = TotalResponse.builder()
                .subtotal(37.96)
                .finalTotal(37.96)
                .discount(0.0)
                .taxes(0.0)
                .build();

        return OrderResponse.builder()
                .orderId("123456")
                .customer(customer)
                .statusOrder(statusEnum)
                .items(List.of(item1, item2))
                .total(total)
                .build();
    }
}
