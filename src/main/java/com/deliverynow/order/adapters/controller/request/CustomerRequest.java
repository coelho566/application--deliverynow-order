package com.deliverynow.order.adapters.controller.request;

import jakarta.validation.Valid;
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
public class CustomerRequest {

    @Schema(description = "Nome da pessoa", example = "João da Silva")
    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    @Schema(description = "Endereço de e-mail", example = "joao.silva@example.com")
    private String email;

    @Valid
    @Schema(description = "Endereço completo")
    private AddressRequest address;
}
