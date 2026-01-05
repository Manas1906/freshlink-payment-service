package com.freshlink.payment.gateway;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GatewayFactory {

    private final Map<String, PaymentGateway> gateways;

    public GatewayFactory(Map<String, PaymentGateway> gateways) {
        this.gateways = gateways;
    }

    public PaymentGateway get(String type) {
        return gateways.get(type);
    }
}
