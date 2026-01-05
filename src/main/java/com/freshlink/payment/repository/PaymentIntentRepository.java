package com.freshlink.payment.repository;

import com.freshlink.payment.model.PaymentIntent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentIntentRepository extends JpaRepository<PaymentIntent, Long> {
}
