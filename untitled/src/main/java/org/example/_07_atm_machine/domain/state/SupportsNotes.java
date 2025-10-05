package org.example._07_atm_machine.domain.state;

import org.example._07_atm_machine.domain.ATM;
import org.example._07_atm_machine.domain.Denomination;

import java.util.Map;

public interface SupportsNotes {
    void processTransaction(ATM atm, long amount, Map<Denomination,Integer> notes);
}
