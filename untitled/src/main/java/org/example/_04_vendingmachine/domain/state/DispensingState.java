package org.example._04_vendingmachine.domain.state;

import org.example._04_vendingmachine.domain.PaymentRequest;
import org.example._04_vendingmachine.domain.Transaction;
import org.example._04_vendingmachine.domain.VendingMachine;

public class DispensingState implements VendingMachineState{


    @Override
    public Transaction processPayment(VendingMachine machine, PaymentRequest request) {
        // Cannot process new payment while dispensing
        System.out.println("DispensingState: Cannot process new payment while dispensing");
        return null;
    }


    @Override
    public void cancelPayment(VendingMachine machine, int transactionId) {
        System.out.println("DispensingState: Cannot cancel payment while dispensing");
        // Cannot cancel payment while dispensing
    }

    @Override
    public String getStateName() {
        return "DISPENSING";
    }

}
