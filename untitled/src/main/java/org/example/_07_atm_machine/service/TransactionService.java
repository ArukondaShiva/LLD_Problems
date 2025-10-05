package org.example._07_atm_machine.service;

import org.example._07_atm_machine.domain.Denomination;
import org.example._07_atm_machine.domain.Transaction;
import org.example._07_atm_machine.domain.TransactionStatus;
import org.example._07_atm_machine.domain.TransactionType;
import org.example._07_atm_machine.domain.strategy.BalanceInquiryStrategy;
import org.example._07_atm_machine.domain.strategy.DepositStrategy;
import org.example._07_atm_machine.domain.strategy.TransactionStrategy;
import org.example._07_atm_machine.domain.strategy.WithdrawalStrategy;
import org.example._07_atm_machine.repository.TransactionRepository;

import java.util.HashMap;
import java.util.Map;

public class TransactionService {

    private TransactionRepository transactionRepository;
    private Map<TransactionType, TransactionStrategy> strategies;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
        this.strategies = new HashMap<>();
        strategies.put(TransactionType.WITHDRAW,new WithdrawalStrategy());
        strategies.put(TransactionType.DEPOSIT,new DepositStrategy());
        strategies.put(TransactionType.BALANCE,new BalanceInquiryStrategy());
    }

    public Transaction showBalance(String sessionId) {
        return processTransaction(sessionId, 0, null, TransactionType.BALANCE);
    }

    public Transaction withdrawCash(String sessionId, long amountMinorUnits) {
        return processTransaction(sessionId, amountMinorUnits, null, TransactionType.WITHDRAW);
    }

    public Transaction depositCash(String sessionId, Map<Denomination, Integer> notes) {
        long amount = calculateAmount(notes);
        return processTransaction(sessionId, amount, notes, TransactionType.DEPOSIT);
    }

    public void acknowledgeTransaction(String transactionId) {
        transactionRepository.updateTransactionStatus(transactionId, TransactionStatus.SUCCESS);
    }

    public boolean validateTransaction(Transaction transaction) {
        // TODO: Implement transaction validation logic
        return true;
    }



    private Transaction processTransaction(String sessionId, long amount, Map<Denomination,Integer> notes,TransactionType type){

        TransactionStrategy strategy = strategies.get(type);
        if(strategy==null){
            throw new IllegalArgumentException("Unsupported transaction type : "+type);
        }

        Transaction transaction = strategy.processTransaction(sessionId,amount,notes);
        return transactionRepository.save(transaction);
    }

    private long calculateAmount(Map<Denomination,Integer> notes){

        if(notes==null){
            return 0;
        }

        return notes.entrySet().stream()
                .mapToLong(entry-> entry.getKey().getValue() * entry.getValue())
                .sum();
    }

}
