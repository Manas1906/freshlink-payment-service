package com.freshlink.payment.service;

import com.freshlink.payment.gateway.PaymentGateway;
import com.freshlink.payment.model.*;
import com.freshlink.payment.repository.PaymentIntentRepository;
import com.freshlink.payment.ledger.LedgerService;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    private final PaymentIntentRepository repo;
    private final LedgerService ledgerService;

    public RefundService(PaymentIntentRepository repo, LedgerService ledgerService) {
        this.repo = repo;
        this.ledgerService = ledgerService;
    }

    public PaymentIntent refund(Long intentId) {

        PaymentIntent intent = repo.findById(intentId).orElseThrow();

        if (intent.getStatus() != PaymentStatus.SUCCESS)
            throw new RuntimeException("Only successful payments can be refunded");

        intent.setStatus(PaymentStatus.REFUNDED);

        ledgerService.recordCredit(intent, "REFUND_" + intentId);

        return repo.save(intent);
    }
}
