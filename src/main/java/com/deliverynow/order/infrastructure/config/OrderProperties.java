package com.deliverynow.order.infrastructure.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "mp.payment")
public interface OrderProperties {

     String user();
     String webhook();
     String external();
     String url();
     String authorization();
}
