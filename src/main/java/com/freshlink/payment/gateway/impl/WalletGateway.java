package com.freshlink.payment.gateway.impl;

import com.freshlink.payment.gateway.PaymentGateway;
import com.freshlink.payment.model.PaymentIntent;
import com.freshlink.payment.model.PaymentResult;
import org.springframework.stereotype.Component;

@Component
public class WalletGateway implements PaymentGateway {

    @Override
    public PaymentResult charge(PaymentIntent intent) {
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
