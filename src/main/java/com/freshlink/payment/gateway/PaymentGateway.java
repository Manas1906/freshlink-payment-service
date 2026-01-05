package com.freshlink.payment.gateway;

import com.freshlink.payment.model.PaymentIntent;
import com.freshlink.payment.model.PaymentResult;

public interface PaymentGateway {
    PaymentResult charge(PaymentIntent intent);
    PaymentResult refund(PaymentIntent intent);
}
