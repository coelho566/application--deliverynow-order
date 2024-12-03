package com.deliverynow.order.adapters.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @Schema(description = "Método de pagamento", example = "Cartão de crédito")
    @NotBlank(message = "O método de pagamento não pode estar em branco")
    private String method;
    private CardDetailsRequest cardDetails;
}
