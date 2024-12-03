package com.deliverynow.order.infrastructure.rest.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private String code;
    private String menssage;
}
