package com.deliverynow.order.adapters.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QrCodePaymentResponse {

    private String inStoreOrderId;
    private String qrCode;
    private String dateExpiration;

}
