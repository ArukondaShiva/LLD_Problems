package org.example._04_vendingmachine.domain.state;

import org.example._04_vendingmachine.domain.PaymentRequest;
import org.example._04_vendingmachine.domain.Transaction;
import org.example._04_vendingmachine.domain.VendingMachine;

public class ProcessPaymentState implements VendingMachineState{


    @Override
    public Transaction processPayment(VendingMachine machine, PaymentRequest request) {
        // Cannot process new payment while already processing
        System.out.println("ProcessingPaymentState: Cannot process new payment while already processing");
        return null;
    }

    @Override
    public void cancelPayment(VendingMachine machine, int transactionId) {
        System.out.println("ProcessingPaymentState: Cancelling payment for transaction " + transactionId);

        Transaction transaction = machine.getCurrentTransaction();

        if(transaction!=null && transaction.getId()==transactionId){
            // Refund the payment
            double amountToRefund = transaction.getAmountInserted();
            // TODO: Implement actual refund logic

            // Cancel the transaction
            transaction.cancel();

            // Clear current transaction
            machine.setCurrentTransaction(null);

            // Return to idle state
            machine.setState(new IdleState());

            System.out.println("ProcessingPaymentState: Payment cancelled, refunded $" + amountToRefund);

        }
        else{
            System.out.println("ProcessingPaymentState: Transaction not found for cancellation");
        }
    }

    @Override
    public String getStateName() {
        return "PROCESSING_PAYMENT";
    }


}
