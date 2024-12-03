package com.deliverynow.order.adapters.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {

    @Schema(description = "Notas adicionais para o pedido", example = "Sem cebola, por favor.")
    private String notes;

    @Schema(description = "Método de entrega do pedido", example = "Retirada no local")
    private String deliveryMethod;
}
