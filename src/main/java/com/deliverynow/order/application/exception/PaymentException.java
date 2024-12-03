package com.deliverynow.order.application.exception;

public class PaymentException extends RuntimeException {
    public PaymentException(Throwable cause) {
        super(cause);
    }

    public PaymentException(String message) {
        super(message);
    }
}
