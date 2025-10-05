package org.example._07_atm_machine.repository;

import org.example._07_atm_machine.domain.CashDrawer;
import org.example._07_atm_machine.domain.Denomination;

import java.util.Map;
import java.util.Optional;
import java.util.Stack;

public interface CashDrawerRepository {

    CashDrawer save(CashDrawer cashDrawer);
    Optional<CashDrawer> findByATMId(String atmId);
    void updateCashInventory(String atmId, Map<Denomination,Integer> notes);

}
