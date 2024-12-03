package com.deliverynow.order.adapters.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrderResponse {
    @Schema(description = "ID do produto", example = "66884a540d50845947ff6349")
    private String itemId;
    @Schema(description = "ID do produto", example = "001")
    private String productId;
    @Schema(description = "Nome do produto", example = "Hambúrguer")
    private String name;
    @Schema(description = "Categoria do produto", example = "LANCHE")
    private String category;
    @Schema(description = "Descrição do produto", example = "Delicioso hambúrguer com queijo, alface, tomate e molho especial.")
    private String description;
    @Schema(description = "Quantidade do produto disponível", example = "10")
    private Integer quantity;
    @Schema(description = "Preço unitário do produto", example = "12.50")
    private Double unitPrice;
    @Schema(description = "Preço total do produto", example = "120.50")
    private Double totalPrice;
}
