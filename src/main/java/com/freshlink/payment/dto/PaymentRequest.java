package com.freshlink.payment.dto;

import com.freshlink.payment.model.PaymentMode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PaymentRequest {
    private Long checkoutId;
    private Double amount;
    private PaymentMode paymentMode; // UPI / CARD / WALLET
}

