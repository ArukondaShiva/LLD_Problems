package org.example._07_atm_machine.service;

import org.example._07_atm_machine.domain.AdminUser;
import org.example._07_atm_machine.domain.CashDrawer;
import org.example._07_atm_machine.domain.Denomination;
import org.example._07_atm_machine.repository.AdminUserRepository;
import org.example._07_atm_machine.repository.CashDrawerRepository;
import org.example._07_atm_machine.repository.TransactionRepository;

import java.util.Map;
import java.util.Optional;

public class AdminService {

    private AdminUserRepository adminUserRepository;
    private CashDrawerRepository cashDrawerRepository;
    private TransactionRepository transactionRepository;


    public AdminService(AdminUserRepository adminUserRepository,
                        CashDrawerRepository cashDrawerRepository,
                        TransactionRepository transactionRepository){
        this.adminUserRepository = adminUserRepository;
        this.cashDrawerRepository = cashDrawerRepository;
        this.transactionRepository = transactionRepository;
    }


    public boolean loginAdmin(String adminId,String pin){
        // TODO: Hash the PIN and compare with stored hash
        Optional<AdminUser> adminOpt = adminUserRepository.findById(adminId);
        if(adminOpt.isPresent()){
            AdminUser admin = adminOpt.get();
            return admin.isActive() && admin.getPinHash().equals(pin);//simple comparison
        }
        return false;
    }


    public void refillCash(String atmId, Map<Denomination,Integer> notes){

        Optional<CashDrawer> drawerOptional = cashDrawerRepository.findByATMId(atmId);

        if(drawerOptional.isPresent()){
            CashDrawer cashDrawer = drawerOptional.get();
            for(Map.Entry<Denomination,Integer> entry : notes.entrySet()){
                cashDrawer.addNotes(entry.getKey(), entry.getValue());
            }
            cashDrawerRepository.save(cashDrawer);
            System.out.println("[AdminService] Cash refilled for ATM: " + atmId);
        }
    }


    public CashDrawer auditCash(String atmId){
        return cashDrawerRepository.findByATMId(atmId).orElse(null);
    }

}
