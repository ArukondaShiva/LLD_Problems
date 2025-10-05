package org.example._07_atm_machine.repository.impl;

import org.example._07_atm_machine.domain.Transaction;
import org.example._07_atm_machine.domain.TransactionStatus;
import org.example._07_atm_machine.repository.TransactionRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TransactionRepositoryImpl implements TransactionRepository {


    private Map<String, Transaction> transactionStore = new ConcurrentHashMap<>();


    @Override
    public Transaction save(Transaction transaction) {
        transactionStore.put(transaction.getId(),transaction);
        return transaction;
    }

    @Override
    public Optional<Transaction> findById(String transactionId) {
        return Optional.ofNullable(transactionStore.get(transactionId));
    }

    @Override
    public List<Transaction> findBySession(String sessionId) {
        return transactionStore.values().stream()
                .filter(txn->sessionId.equals(txn.getSessionId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findByATMAndTimeRange(String atmId, long startTime, long endTime) {
        return transactionStore.values().stream()
                .filter(txn->atmId.equals(txn.getAtmId()))
                .filter(txn->txn.getCreatedAt()>=startTime && txn.getCreatedAt()<=endTime)
                .collect(Collectors.toList());
    }

    @Override
    public void updateTransactionStatus(String transactionId, TransactionStatus status) {
        Optional<Transaction> txnOpt = findById(transactionId);
        if(txnOpt.isPresent()){
            txnOpt.get().setStatus(status);
        }
    }
}
