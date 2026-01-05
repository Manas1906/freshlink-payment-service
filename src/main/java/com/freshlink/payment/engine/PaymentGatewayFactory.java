package com.freshlink.payment.engine;

import com.freshlink.payment.gateway.*;
import com.freshlink.payment.gateway.impl.CardGateway;
import com.freshlink.payment.gateway.impl.UpiGateway;
import com.freshlink.payment.gateway.impl.WalletGateway;
import com.freshlink.payment.model.PaymentMode;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayFactory {

    private final UpiGateway upi;
    private final CardGateway card;
    private final WalletGateway wallet;

    public PaymentGatewayFactory(UpiGateway upi, CardGateway card, WalletGateway wallet) {
        this.upi = upi;
        this.card = card;
        this.wallet = wallet;
    }

    public PaymentGateway getGateway(PaymentMode mode) {
        return switch (mode) {
            case UPI -> upi;
            case CARD -> card;
            case WALLET -> wallet;
        };
    }
}
