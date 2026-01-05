package com.freshlink.payment.gateway.impl;

import com.freshlink.payment.gateway.PaymentGateway;
import com.freshlink.payment.model.PaymentIntent;
import com.freshlink.payment.model.PaymentResult;
import org.springframework.stereotype.Component;

@Component
public class CardGateway implements PaymentGateway {

    @Override
    public PaymentResult charge(PaymentIntent intent) {
        return new PaymentResult(
                true,
                "CARD_" + System.currentTimeMillis(),
                "Card payment successful"
        );
    }

    @Override
    public PaymentResult refund(PaymentIntent intent) {
        return new PaymentResult(
                true,
                "CARD_REF_" + System.currentTimeMillis(),
                "Card refund successful"
        );
    }
}
