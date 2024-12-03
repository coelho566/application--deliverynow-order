package com.deliverynow.order.adapters.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebHookPayload {

    private String resource;
    private String topic;
}
