package com.freshlink.payment.controller;

import com.freshlink.payment.dto.PaymentRequest;
import com.freshlink.payment.model.*;
import com.freshlink.payment.repository.PaymentIntentRepository;
import com.freshlink.payment.service.PaymentProcessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentProcessor processor;

    public PaymentController(PaymentProcessor processor) {
        this.processor = processor;
    }

    @PostMapping("/pay")
    public PaymentIntent pay(@AuthenticationPrincipal String email,
                             @RequestBody PaymentRequest req) {

        PaymentIntent intent = PaymentIntent.builder()
                .checkoutId(req.getCheckoutId())
                .customerEmail(email)
                .amount(req.getAmount())
                .paymentMode(req.getPaymentMode())
                .status(PaymentStatus.CREATED)
                .build();

        return processor.process(intent);
    }
}
