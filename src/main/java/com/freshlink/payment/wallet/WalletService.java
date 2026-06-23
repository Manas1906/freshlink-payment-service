package com.freshlink.payment.wallet;

import com.freshlink.payment.model.LedgerEntry;
import com.freshlink.payment.model.LedgerType;
import com.freshlink.payment.repository.LedgerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {

    private final LedgerRepository ledgerRepo;

    public WalletService(LedgerRepository ledgerRepo) {
        this.ledgerRepo = ledgerRepo;
    }

    @Transactional
    public double getBalance(String email) {
        return ledgerRepo.findTopByUserEmailOrderByIdDesc(email)
                .map(LedgerEntry::getBalanceAfter)
                .orElseGet(() -> {
                    double initialBalance = 5000.0;
                    LedgerEntry seed = LedgerEntry.builder()
                            .intentId(0L)
                            .userEmail(email)
                            .type(LedgerType.CREDIT)
                            .amount(initialBalance)
                            .balanceAfter(initialBalance)
                            .reference("SEED_BALANCE")
                            .description("Initial onboarding test balance")
                            .build();
                    ledgerRepo.save(seed);
                    return initialBalance;
                });
    }
}
