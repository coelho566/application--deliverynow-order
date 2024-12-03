package com.deliverynow.order.adapters.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalRequest {

    @Schema(description = "Subtotal", example = "100.00")
    @NotNull(message = "O subtotal não pode ser nulo")
    private Double subtotal;

    @Schema(description = "Impostos", example = "10.00")
    private Double taxes;

    @Schema(description = "Descontos", example = "5.00")
    private Double discounts;

    @Schema(description = "Total final", example = "105.00")
    @NotNull(message = "O total final não pode ser nulo")
    private Double finalTotal;
}
