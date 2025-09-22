package org.example._01_parkinglot.controller;

import org.example._01_parkinglot.service.AdminService;

public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
        System.out.println();
        System.out.println("[CONTROLLER] AdminController initialized");
    }

}
