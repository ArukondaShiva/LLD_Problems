package org.example._07_atm_machine.domain.state;

import org.example._07_atm_machine.domain.ATM;
import org.example._07_atm_machine.domain.TransactionType;
import org.example._07_atm_machine.domain.exception.InvalidATMOperationException;

public abstract class AbstractATMState implements ATMState{


    @Override
    public void insertCard(ATM atm, String cardId) throws InvalidATMOperationException {
        throw new InvalidATMOperationException("Operation not allowed in "+getClass().getSimpleName());
    }

    @Override
    public void ejectCard(ATM atm) throws InvalidATMOperationException {
        throw new InvalidATMOperationException("Operation not allowed in "+getClass().getSimpleName());
    }

    @Override
    public void enterPin(ATM atm, String pin) throws InvalidATMOperationException {
        throw new InvalidATMOperationException("Operation not allowed in "+getClass().getSimpleName());
    }

    @Override
    public void selectTransaction(ATM atm, TransactionType type) throws InvalidATMOperationException {
        throw new InvalidATMOperationException("Operation not allowed in "+getClass().getSimpleName());
    }

    @Override
    public void processTransaction(ATM atm, long amount) throws InvalidATMOperationException {
        throw new InvalidATMOperationException("Operation not allowed in "+getClass().getSimpleName());
    }

    @Override
    public void endSession(ATM atm) throws InvalidATMOperationException {
        throw new InvalidATMOperationException("Operation not allowed in "+getClass().getSimpleName());
    }

    @Override
    public ATMState next(ATM atm) {
        // Default: remain in current state
        return this;
    }


}
