package org.example._07_atm_machine.controller;

import org.example._07_atm_machine.domain.CashDrawer;
import org.example._07_atm_machine.service.ATMService;

public class ATMController {

    private ATMService atmService;

    public ATMController(ATMService atmService){
        this.atmService = atmService;
    }

    public void takeOffline(String atmId){
        atmService.takeOffline(atmId);
    }

    public void takeOnline(String atmId){
        atmService.bringOnline(atmId);
    }

    public CashDrawer auditCash(String atmId){
        return atmService.auditCash(atmId);
    }

}
