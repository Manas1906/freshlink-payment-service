package com.freshlink.payment.controller;

import com.freshlink.payment.wallet.WalletService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/balance")
    public double balance(@AuthenticationPrincipal String email) {
        return walletService.getBalance(email);
    }
}
