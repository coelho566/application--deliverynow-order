package com.deliverynow.order.adapters.gateway.rest;


import com.deliverynow.order.application.gateway.QrCodePaymentGateway;
import com.deliverynow.order.domain.entity.Order;
import com.deliverynow.order.infrastructure.config.OrderProperties;
import com.deliverynow.order.infrastructure.rest.QrCodeRest;
import com.deliverynow.order.infrastructure.rest.request.QrCodeItemRequest;
import com.deliverynow.order.infrastructure.rest.request.QrCodeRequest;
import com.deliverynow.order.infrastructure.rest.response.QrCodeResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class QrCodePaymentRestGateway implements QrCodePaymentGateway {

    @Inject
    @RestClient
    QrCodeRest paymentRest;
    @Inject
    OrderProperties orderProperties;

    @Override
    public QrCodeResponse processPayment(Order oder) {
        var paymentResquest = buildPaymentRequest(oder);
        var auth = String.format("Bearer %s", orderProperties.authorization());
        log.info("Iniciando o processamento do pagamento {}", paymentResquest.getNotificationUrl());
        return paymentRest.generatedQrCode(auth, orderProperties.user(), orderProperties.external(), paymentResquest);
    }

    private QrCodeRequest buildPaymentRequest(Order oder) {

        return QrCodeRequest.builder()
                .description("Delivery Now payment")
                .externalReference(oder.getOrderId())
                .expirationDate(getExpirationDate())
                .items(buildItemsPayment(oder))
                .notificationUrl(orderProperties.webhook())
                .title("Product order")
                .totalAmount(oder.getTotal().getFinalTotal())
                .build();
    }

    private String getExpirationDate() {
        var localDateTime = LocalDateTime.now().plusMinutes(10L);
        var zoneId = ZoneId.of("America/New_York"); // Exemplo de um fuso horário com -04:00
        var zonedDateTime = localDateTime.atZone(zoneId);
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return zonedDateTime.format(formatter);
    }

    private List<QrCodeItemRequest> buildItemsPayment(Order oder) {
        return oder.getItems().stream()
                .map(item -> QrCodeItemRequest.builder()
                        .skuNumber(item.getItemId())
                        .category(item.getCategory())
                        .title(item.getName())
                        .description(item.getDescription())
                        .unitPrice(item.getUnitPrice())
                        .quantity(item.getQuantity())
                        .unitMeasure("unit")
                        .totalAmount(item.getTotalPrice())
                        .build()).collect(Collectors.toList());
    }
}
