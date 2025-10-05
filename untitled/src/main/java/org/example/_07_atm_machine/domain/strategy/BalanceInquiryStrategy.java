package org.example._07_atm_machine.domain.strategy;

import org.example._07_atm_machine.domain.*;
import org.example._07_atm_machine.repository.SessionRepository;
import org.example._07_atm_machine.repository.impl.SessionRepositoryImpl;

import java.util.Map;

public class BalanceInquiryStrategy implements TransactionStrategy{


    private SessionRepository sessionRepository;

    public BalanceInquiryStrategy(){
        this.sessionRepository = new SessionRepositoryImpl();
    }

    @Override
    public Transaction processTransaction(String sessionId, long amount, Map<Denomination, Integer> notes) {

        Session curSession = sessionRepository.findById(sessionId).orElse(null);
        if(curSession==null){
            return null;
        }


        // TODO: Get account balance from bank server
        System.out.println("[BalanceInquiryStrategy] Processing balance inquiry for session: " + sessionId);


        Transaction transaction = new Transaction(
                "TXN_" + System.currentTimeMillis(),
                curSession.getAtmId(), // TODO: Get from session
                sessionId,
                curSession.getAccountId(), // TODO: Get from session
                TransactionType.BALANCE,
                0
        );


        // TODO: Set actual balance from account
        transaction.setStatus(TransactionStatus.SUCCESS);
        System.out.println("[BalanceInquiryStrategy] Balance inquiry completed");

        return transaction;
    }


}
