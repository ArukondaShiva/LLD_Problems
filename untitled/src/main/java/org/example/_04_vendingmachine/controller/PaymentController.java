package org.example._04_vendingmachine.controller;

import org.example._04_vendingmachine.domain.Denomination;
import org.example._04_vendingmachine.domain.PaymentRequest;
import org.example._04_vendingmachine.domain.Transaction;
import org.example._04_vendingmachine.service.PaymentService;

import java.util.List;
import java.util.Map;

public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
        System.out.println("PaymentController initialized");
    }

    public Transaction processPayment(int machineId, PaymentRequest request){
        System.out.println("Controller: Processing payment for machine " + machineId + ", product " + request.getProductId());
        return paymentService.processPayment(machineId,request);
    }

    public void cancelPayment(int machineId,int transactionId){
        System.out.println("Controller: Cancelling payment for machine " + machineId + ", transaction " + transactionId);
        paymentService.cancelPayment(machineId, transactionId);
    }

    public String getPaymentStatus(int machineId, int transactionId) {
        System.out.println("Controller: Getting payment status for machine " + machineId + ", transaction " + transactionId);
        return paymentService.getPaymentStatus(machineId, transactionId);
    }


    public List<Transaction> getTransactionHistory(int machineId){
        System.out.println("Controller: Getting transaction history for machine " + machineId);
        return paymentService.getTransactionHistory(machineId);
    }


    public double getTotalCashInMachine(int machineId) {
        System.out.println("Controller: Getting total cash in machine " + machineId);
        return paymentService.getTotalCashInMachine(machineId);
    }

    public Map<Denomination, Integer> getCashBoxStatus(int machineId) {
        System.out.println("Controller: Getting cash box status for machine " + machineId);
        return paymentService.getCashBoxStatus(machineId);
    }

}
