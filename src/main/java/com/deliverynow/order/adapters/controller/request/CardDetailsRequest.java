package com.deliverynow.order.adapters.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDetailsRequest {
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String cardholderName;
}
