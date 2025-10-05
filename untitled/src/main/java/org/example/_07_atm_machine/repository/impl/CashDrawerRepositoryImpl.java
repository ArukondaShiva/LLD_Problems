package org.example._07_atm_machine.repository.impl;

import org.example._07_atm_machine.domain.Card;
import org.example._07_atm_machine.domain.CashDrawer;
import org.example._07_atm_machine.domain.Denomination;
import org.example._07_atm_machine.repository.CardRepository;
import org.example._07_atm_machine.repository.CashDrawerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CashDrawerRepositoryImpl implements CashDrawerRepository {

    private Map<String, CashDrawer> cashDrawerStore = new HashMap<>();


    @Override
    public CashDrawer save(CashDrawer cashDrawer) {
        cashDrawerStore.put(cashDrawer.getAtmId(),cashDrawer);
        return cashDrawer;
    }

    @Override
    public Optional<CashDrawer> findByATMId(String atmId) {
        return Optional.ofNullable(cashDrawerStore.get(atmId));
    }

    @Override
    public void updateCashInventory(String atmId, Map<Denomination, Integer> notes) {
        Optional<CashDrawer> drawerOpt = findByATMId(atmId);
        if(drawerOpt.isPresent()){
            CashDrawer drawer = drawerOpt.get();
            drawer.setNotesByDenomination(new HashMap<>(notes));
        }
    }
}
