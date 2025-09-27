package org.example._04_vendingmachine.domain.state;

import org.example._04_vendingmachine.domain.*;

import java.util.Map;

public class IdleState implements VendingMachineState{


    @Override
    public Transaction processPayment(VendingMachine machine, PaymentRequest request) {

        System.out.println("IdleState: Processing payment request for product " + request.getProductId());

        // TODO: Validate product exists and has sufficient stock
        // TODO: Check if machine has sufficient change

        // Create transaction
        Transaction transaction = new Transaction(0,machine.getId(),request.getProductId(),request.getTotalAmount());
        machine.setCurrentTransaction(transaction);


        // Process Payment
        double amountInserted = request.getTotalAmount();
        transaction.addPayment(amountInserted);


        // Add cash to the machine
        for(Map.Entry<Denomination,Integer> entry : request.getDenominations().entrySet()){
            machine.addCash(entry.getKey(), entry.getValue());
        }


        // Change to processing payment state
        machine.setState(new ProcessPaymentState());


        // Simulate payment processing and then transition to dispensing state
        try {

            Thread.sleep(1000); // // Simulate payment processing time
            machine.setState(new DispensingState());

            // Dispense the product
            Product product = machine.getInventory().keySet().stream()
                    .filter(p->p.getId()==request.getProductId())
                    .findFirst()
                    .orElse(null);


            if(product!=null){
                machine.dispenseProduct(product);
                // Change state back to IdleState after dispensing
                machine.setState(new IdleState());
            }


        }catch (InterruptedException e){
            System.out.println("IdleState: Payment processing interrupted");
            machine.setState(new IdleState());
        }

        System.out.println("IdleState: Payment processed, transaction created: " + transaction.getId());
        return transaction;
    }

    @Override
    public void cancelPayment(VendingMachine machine, int transactionId) {
        // No action needed in idle state
        System.out.println("IdleState: No active transaction to cancel");
    }

    @Override
    public String getStateName() {
        return "IDLE";
    }


}
