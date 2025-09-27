package org.example._04_vendingmachine.domain;

public class Inventory {


    // We don't need this class as we are maintaining inventory map by storing product and it's count in vendingMachine class.

/*
    private int productId;
    private int vendingMachineId;
    private int quantity;
    private int minThreshold;


    public Inventory(int productId,int vendingMachineId,int quantity,int minThreshold){
        this.productId = productId;
        this.vendingMachineId = vendingMachineId;
        this.quantity = quantity;
        this.minThreshold = minThreshold;
    }

 */


    /*
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getVendingMachineId() {
        return vendingMachineId;
    }

    public void setVendingMachineId(int vendingMachineId) {
        this.vendingMachineId = vendingMachineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinThreshold() {
        return minThreshold;
    }

    public void setMinThreshold(int minThreshold) {
        this.minThreshold = minThreshold;
    }

    public boolean isLowStock(){
        return quantity<=minThreshold;
    }

    public boolean isOutOfStock(){
        return quantity <= 0;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

    public void removeQuantity(int amount){
        if(this.quantity >= amount){
            this.quantity -= amount;
        }else {
            this.quantity = 0;
        }
    }


    @Override
    public String toString() {
        return "Inventory{" +
                "productId=" + productId +
                ", vendingMachineId=" + vendingMachineId +
                ", quantity=" + quantity +
                ", minThreshold=" + minThreshold +
                '}';
    }

     */


}
