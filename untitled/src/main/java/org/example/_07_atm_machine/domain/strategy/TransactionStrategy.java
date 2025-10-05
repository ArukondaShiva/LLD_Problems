package org.example._07_atm_machine.domain.strategy;

import org.example._07_atm_machine.domain.Denomination;
import org.example._07_atm_machine.domain.Transaction;

import java.util.Map;

public interface TransactionStrategy {

    Transaction processTransaction(String sessionId, long amount, Map<Denomination,Integer> notes);

}
