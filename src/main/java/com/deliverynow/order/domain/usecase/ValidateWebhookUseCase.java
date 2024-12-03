package com.deliverynow.order.domain.usecase;

import java.util.Optional;

public interface ValidateWebhookUseCase {

    Optional<String> getPaymentIdOrder(String payload);
}
