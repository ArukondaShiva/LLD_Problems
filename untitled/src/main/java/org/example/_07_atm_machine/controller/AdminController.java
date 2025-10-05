package org.example._07_atm_machine.controller;


import org.example._07_atm_machine.domain.CashDrawer;
import org.example._07_atm_machine.domain.Denomination;
import org.example._07_atm_machine.service.AdminService;

import java.util.Map;

public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    public boolean loginAdmin(String adminId,String pin){
        return adminService.loginAdmin(adminId,pin);
    }

    public void refillCash(String atmId, Map<Denomination,Integer> notes){
        adminService.refillCash(atmId, notes);
    }

    public CashDrawer auditCash(String atnId){
        return adminService.auditCash(atnId);
    }

}
