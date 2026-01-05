package com.freshlink.payment.controller;

import com.freshlink.payment.model.PaymentIntent;
import com.freshlink.payment.service.RefundService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class RefundController {

    private final RefundService refundService;

    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }

    @PostMapping("/refund/{intentId}")
    public PaymentIntent refund(@PathVariable Long intentId) {
        return refundService.refund(intentId);
    }
}
