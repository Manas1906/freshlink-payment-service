package com.freshlink.payment.wallet;

import com.freshlink.payment.repository.LedgerRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final LedgerRepository ledgerRepo;

    public WalletService(LedgerRepository ledgerRepo) {
        this.ledgerRepo = ledgerRepo;
    }

    public double getBalance(String email) {
        return ledgerRepo.findTopByUserEmailOrderByIdDesc(email)
                .map(e -> e.getBalanceAfter())
                .orElse(0.0);
    }
}
