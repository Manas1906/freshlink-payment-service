package com.freshlink.payment.gateway.impl;

import com.freshlink.payment.gateway.*;
import com.freshlink.payment.model.PaymentIntent;
import com.freshlink.payment.model.PaymentResult;
import org.springframework.stereotype.Component;

@Component("UPI")
public class UpiGateway implements PaymentGateway {

    @Override
    public PaymentResult charge(PaymentIntent intent) {
        return new PaymentResult(true,
                "UPI_" + System.currentTimeMillis(),
                "UPI payment success");
    }

    @Override
    public PaymentResult refund(PaymentIntent intent) {
        return new PaymentResult(true,
                "UPI_REF_" + System.currentTimeMillis(),
                "UPI refund success");
    }
}

