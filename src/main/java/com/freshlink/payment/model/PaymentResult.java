package com.freshlink.payment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResult {
    private boolean success;
    private String gatewayTxnId;
    private String message;
}
