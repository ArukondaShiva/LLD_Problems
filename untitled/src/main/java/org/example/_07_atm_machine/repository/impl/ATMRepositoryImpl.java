package org.example._07_atm_machine.repository.impl;

import org.example._07_atm_machine.domain.ATM;
import org.example._07_atm_machine.domain.state.ATMState;
import org.example._07_atm_machine.repository.ATMRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ATMRepositoryImpl implements ATMRepository {

    private Map<String,ATM> atmStore = new ConcurrentHashMap<>();

    @Override
    public ATM save(ATM atm) {
        atmStore.put(atm.getId(),atm);
        return atm;
    }

    @Override
    public Optional<ATM> findById(String atmId) {
        return Optional.ofNullable(atmStore.get(atmId));
    }

    @Override
    public List<ATM> findAll() {
        return new ArrayList<>(atmStore.values());
    }

    @Override
    public void updateATMState(String atmId, ATMState atmState) {
        Optional<ATM> atmOpt = findById(atmId);
        if(atmOpt.isPresent()){
            atmOpt.get().setCurrentState(atmState);
        }
    }
}
