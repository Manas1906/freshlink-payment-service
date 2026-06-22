package com.freshlink.payment.ledger;

import com.freshlink.payment.model.*;
import com.freshlink.payment.repository.LedgerRepository;
import org.springframework.stereotype.Service;

@Service
public class LedgerService {

    private final LedgerRepository repo;

    public LedgerService(LedgerRepository repo) {
        this.repo = repo;
    }

    public void recordDebit(PaymentIntent intent, String gatewayTxnId) {

        double prev = repo.findTopByUserEmailOrderByIdDesc(intent.getCustomerEmail())
                .map(LedgerEntry::getBalanceAfter).orElse(0.0);

        double newBal = prev - intent.getAmount();

        LedgerEntry entry = LedgerEntry.builder()
                .intentId(intent.getId())
                .userEmail(intent.getCustomerEmail())
                .type(LedgerType.DEBIT)
                .amount(intent.getAmount())
                .balanceAfter(newBal)
                .gatewayTxnId(gatewayTxnId)
                .reference("PAY_" + intent.getId())
                .description("Payment debit")
                .build();

        repo.save(entry);
    }


    public void recordCredit(PaymentIntent intent, String ref) {
        double prev = repo.findTopByUserEmailOrderByIdDesc(intent.getCustomerEmail())
                .map(LedgerEntry::getBalanceAfter).orElse(0.0);

        double newBal = prev + intent.getAmount();

        LedgerEntry entry = LedgerEntry.builder()
                .intentId(intent.getId())
                .userEmail(intent.getCustomerEmail())
                .type(LedgerType.CREDIT)
                .amount(intent.getAmount())
                .balanceAfter(newBal)
                .reference(ref)
                .description("Payment credit")
                .build();
        repo.save(entry);
    }
}
