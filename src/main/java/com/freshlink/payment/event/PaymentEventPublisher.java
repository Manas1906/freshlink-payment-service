package com.freshlink.payment.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.freshlink.event.payment.PaymentCompletedEvent;
import com.freshlink.event.payment.PaymentFailedEvent;


@Component
public class PaymentEventPublisher {

    private final ApplicationEventPublisher publisher;

    public PaymentEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void paymentSuccess(PaymentCompletedEvent e) {
        publisher.publishEvent(e);
    }

    public void paymentFailed(PaymentFailedEvent e) {
        publisher.publishEvent(e);
    }
}
