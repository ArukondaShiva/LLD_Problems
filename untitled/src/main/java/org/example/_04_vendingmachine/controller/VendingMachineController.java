package org.example._04_vendingmachine.controller;

import org.example._04_vendingmachine.domain.Product;
import org.example._04_vendingmachine.service.VendingMachineService;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineController {

    private VendingMachineService vendingMachineService;

    public VendingMachineController(VendingMachineService vendingMachineService){
        this.vendingMachineService = vendingMachineService;
    }

    public List<Product> getAvailableProducts(int machineId) {
        return vendingMachineService.getAvailableProducts(machineId);
        //List<Product> products = new ArrayList<>();
        //return products;
    }
}
