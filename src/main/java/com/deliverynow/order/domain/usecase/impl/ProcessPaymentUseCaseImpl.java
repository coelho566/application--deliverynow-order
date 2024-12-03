package com.deliverynow.order.domain.usecase.impl;

import com.deliverynow.order.adapters.gateway.repository.PaymentRepositoryGateway;
import com.deliverynow.order.application.exception.PaymentException;
import com.deliverynow.order.application.gateway.ProcessPaymentGateway;
import com.deliverynow.order.application.presenter.PaymentPresenter;
import com.deliverynow.order.domain.entity.OrderStatusEnum;
import com.deliverynow.order.domain.usecase.ProcessPaymentUseCase;
import com.deliverynow.order.domain.usecase.UpdateStatusOrderUseCase;
import com.deliverynow.order.infrastructure.rest.response.MerchantOrderResponse;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProcessPaymentUseCaseImpl implements ProcessPaymentUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ProcessPaymentUseCaseImpl.class);

    PaymentPresenter paymentPresenter;
    ProcessPaymentGateway processPaymentGateway;
    UpdateStatusOrderUseCase updateStatusOrderUseCase;
    PaymentRepositoryGateway paymentRepositoryGateway;
    Cache<String, Boolean> processedIdsCache;

    public ProcessPaymentUseCaseImpl(PaymentPresenter paymentPresenter, ProcessPaymentGateway processPaymentGateway, UpdateStatusOrderUseCase updateStatusOrderUseCase, PaymentRepositoryGateway paymentRepositoryGateway) {
        this.paymentPresenter = paymentPresenter;
        this.processPaymentGateway = processPaymentGateway;
        this.updateStatusOrderUseCase = updateStatusOrderUseCase;
        this.paymentRepositoryGateway = paymentRepositoryGateway;
        this.processedIdsCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public void execute(String orderId) {

        if (processedIdsCache.getIfPresent(orderId) == null) {
            processedIdsCache.put(orderId, Boolean.TRUE);

            logger.info("Starting payment process for id {}", orderId);
            var scheduler = Executors.newSingleThreadScheduledExecutor();
            var future = new CompletableFuture<MerchantOrderResponse>();
            Runnable task = getTask(orderId, future);
            try {
                scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
                var paymentMpResponse = future.get();
                var payment = paymentPresenter.paymentMerchantToDomain(paymentMpResponse.getMerchantPayments().getFirst());
                payment.setOrderId(paymentMpResponse.getExternalReference());
                paymentRepositoryGateway.insertPayment(payment);
                updateStatusOrderUseCase.updateStatus(paymentMpResponse.getExternalReference(), OrderStatusEnum.RECEBIDO.name());

            } catch (InterruptedException | ExecutionException e) {
                logger.error("Error processing payment", e);
                Thread.currentThread().interrupt();
                throw new PaymentException("Error processing payment");
            } finally {
                logger.info("Payment for id {} completed", orderId);
                scheduler.shutdown();
            }
        } else {
            logger.info("Order {} is already in processing", orderId);
        }

    }

    private Runnable getTask(String orderId, CompletableFuture<MerchantOrderResponse> future) {
        return () -> {
            try {
                var status = processPaymentGateway.processPayment(orderId);
                if ("CLOSED".equalsIgnoreCase(status.getStatus())) {
                    logger.info("Payment processed successfully {}", orderId);
                    future.complete(status);
                }
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        };
    }
}
