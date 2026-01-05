package com.freshlink.payment.service;

import com.freshlink.event.payment.PaymentCompletedEvent;
import com.freshlink.event.payment.PaymentFailedEvent;
import com.freshlink.payment.engine.PaymentGatewayFactory;
import com.freshlink.payment.event.PaymentEventPublisher;
import com.freshlink.payment.gateway.PaymentGateway;
import com.freshlink.payment.ledger.LedgerService;
import com.freshlink.payment.model.*;
import com.freshlink.payment.repository.PaymentIntentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentProcessor {

    private final PaymentIntentRepository intentRepo;
    private final LedgerService ledgerService;
    private final PaymentGatewayFactory factory;
    private final PaymentEventPublisher publisher;

    public PaymentProcessor(PaymentIntentRepository intentRepo,
                            LedgerService ledgerService,
                            PaymentGatewayFactory factory,
                            PaymentEventPublisher publisher) {
        this.intentRepo = intentRepo;
        this.ledgerService = ledgerService;
        this.factory = factory;
        this.publisher = publisher;
    }

    @Transactional
    public PaymentIntent process(PaymentIntent intent) {

        intent.setStatus(PaymentStatus.PROCESSING);
        intentRepo.save(intent);

        try {
            PaymentGateway gateway = factory.getGateway(intent.getPaymentMode());
            PaymentResult result = gateway.charge(intent);

            if (!result.isSuccess()) {
                intent.setStatus(PaymentStatus.FAILED);
                intentRepo.save(intent);

                publisher.paymentFailed(
                        new PaymentFailedEvent(
                                intent.getId(),
                                intent.getCheckoutId(),
                                intent.getCustomerEmail(),
                                result.getMessage()
                        )
                );
                return intent;
            }

            intent.setGatewayTxnId(result.getGatewayTxnId());
            intent.setStatus(PaymentStatus.SUCCESS);
            intentRepo.save(intent);

            ledgerService.recordDebit(intent, result.getGatewayTxnId());

            publisher.paymentSuccess(
                    new PaymentCompletedEvent(
                            intent.getId(),
                            intent.getCheckoutId(),
                            intent.getCustomerEmail(),
                            intent.getAmount()
                    )
            );

            return intent;

        } catch (Exception ex) {
            intent.setStatus(PaymentStatus.FAILED);
            intentRepo.save(intent);

            publisher.paymentFailed(
                    new PaymentFailedEvent(
                            intent.getId(),
                            intent.getCheckoutId(),
                            intent.getCustomerEmail(),
                            ex.getMessage()
                    )
            );
            throw ex;
        }
    }
}
