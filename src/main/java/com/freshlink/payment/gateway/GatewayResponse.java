package com.freshlink.payment.gateway;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GatewayResponse {

    private boolean success;
    private String gatewayTxnId;
    private String message;
}
