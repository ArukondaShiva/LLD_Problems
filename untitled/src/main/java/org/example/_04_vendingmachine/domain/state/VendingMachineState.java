package org.example._04_vendingmachine.domain.state;

import org.example._04_vendingmachine.domain.PaymentRequest;
import org.example._04_vendingmachine.domain.Transaction;
import org.example._04_vendingmachine.domain.VendingMachine;

public interface VendingMachineState {

    Transaction processPayment(VendingMachine machine, PaymentRequest request);
    void cancelPayment(VendingMachine machine,int transactionId);
    String getStateName();

}
