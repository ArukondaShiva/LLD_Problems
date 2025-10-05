package org.example._07_atm_machine.repository;

import org.example._07_atm_machine.domain.ATM;
import org.example._07_atm_machine.domain.state.ATMState;

import java.util.List;
import java.util.Optional;

public interface ATMRepository {
    ATM save(ATM atm);
    Optional<ATM> findById(String atmId);
    List<ATM> findAll();
    void updateATMState(String atmId, ATMState atmState);
}
