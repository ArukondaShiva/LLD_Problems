package org.example._01_parkinglot.adapter;

import java.util.UUID;

public interface PaymentGatewayAdapter {
    boolean pay(UUID ticketId,double amount);
}
