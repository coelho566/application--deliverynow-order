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
public class AddressRequest {

    @Schema(description = "Código Postal", example = "12345-678")
    @NotBlank(message = "O CEP não pode estar em branco")
    private String postalCode;

    @Schema(description = "Nome da rua", example = "Av. Paulista")
    @NotBlank(message = "A rua não pode estar em branco")
    private String street;

    @Schema(description = "Nome do estado", example = "SP")
    @NotBlank(message = "O estado não pode estar em branco")
    private String state;

    @Schema(description = "Nome da cidade", example = "São Paulo")
    @NotBlank(message = "A cidade não pode estar em branco")
    private String city;

    @Schema(description = "Número da residência", example = "1000")
    @NotBlank(message = "O numero não pode estar em branco")
    private String number;
}
