package com.freshlink.payment.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_intent")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentIntent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMode paymentMode;   // UPI / CARD / WALLET

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;       // CREATED / PROCESSING / SUCCESS / FAILED

    private String gatewayTxnId;
    @Column(nullable = false)
    private Long checkoutId;

    private String failureReason;

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        status = PaymentStatus.CREATED;
    }
}
