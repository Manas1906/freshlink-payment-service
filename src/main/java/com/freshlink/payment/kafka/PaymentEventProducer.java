package com.freshlink.payment.kafka;

import com.freshlink.event.payment.PaymentCompletedEvent;
import com.freshlink.event.payment.PaymentFailedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void success(PaymentCompletedEvent event) {
        try {
            kafkaTemplate.send("payment.success", event);
        } catch (Exception e) {
            System.err.println("KAFKA BROKER DOWN: Could not send PaymentCompletedEvent. " + e.getMessage());
        }
    }

    public void failed(PaymentFailedEvent event) {
        try {
            kafkaTemplate.send("payment.failed", event);
        } catch (Exception e) {
            System.err.println("KAFKA BROKER DOWN: Could not send PaymentFailedEvent. " + e.getMessage());
        }
    }
}
