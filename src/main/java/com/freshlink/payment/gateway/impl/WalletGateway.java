package com.freshlink.payment.gateway.impl;

import com.freshlink.payment.gateway.PaymentGateway;
import com.freshlink.payment.model.PaymentIntent;
import com.freshlink.payment.model.PaymentResult;
import com.freshlink.payment.wallet.WalletService;
import org.springframework.stereotype.Component;

@Component
public class WalletGateway implements PaymentGateway {

    private final WalletService walletService;

    public WalletGateway(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public PaymentResult charge(PaymentIntent intent) {
        double balance = walletService.getBalance(intent.getCustomerEmail());
        if (balance < intent.getAmount()) {
            return new PaymentResult(
                    false,
                    null,
                    "Insufficient wallet balance. Current: " + balance + ", Required: " + intent.getAmount()
            );
        }
        return new PaymentResult(
                true,
                "WALLET_" + System.currentTimeMillis(),
                "Wallet payment successful"
        );
    }

    @Override
    public PaymentResult refund(PaymentIntent intent) {
        return new PaymentResult(
                true,
                "WALLET_REF_" + System.currentTimeMillis(),
                "Wallet refund successful"
        );
    }
}
