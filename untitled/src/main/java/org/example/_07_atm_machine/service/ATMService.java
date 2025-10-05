package org.example._07_atm_machine.service;

import org.example._07_atm_machine.domain.ATM;
import org.example._07_atm_machine.domain.CashDrawer;
import org.example._07_atm_machine.domain.state.IdleState;
import org.example._07_atm_machine.domain.state.OutOfServiceState;
import org.example._07_atm_machine.repository.ATMRepository;
import org.example._07_atm_machine.repository.CashDrawerRepository;

import java.util.Optional;

public class ATMService {

    private ATMRepository atmRepository;
    private CashDrawerRepository cashDrawerRepository;

    public ATMService(ATMRepository atmRepository,CashDrawerRepository cashDrawerRepository){
        this.atmRepository = atmRepository;
        this.cashDrawerRepository = cashDrawerRepository;
    }

    public void takeOffline(String atmId){
        Optional<ATM> atmOpt = atmRepository.findById(atmId);
        if(atmOpt.isPresent()){
            ATM atm = atmOpt.get();
            atm.setOnline(false);
            atm.setCurrentState(new OutOfServiceState());
            atmRepository.save(atm);
        }
    }

    public void bringOnline(String atmId){
        Optional<ATM> atmOpt = atmRepository.findById(atmId);
        if(atmOpt.isPresent()){
            ATM atm = atmOpt.get();
            atm.setOnline(true);
            atm.setCurrentState(new IdleState());
            atmRepository.save(atm);
        }
    }


    public CashDrawer auditCash(String atmId){
        return cashDrawerRepository.findByATMId(atmId).orElse(null);
    }

    public ATM getATM(String atmId){
        return atmRepository.findById(atmId).orElse(null);
    }

}
