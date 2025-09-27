package org.example._04_vendingmachine.domain;
public enum Denomination {

    ONE_DOLLAR(100),
    FIVE_DOLLAR(500),
    TEN_DOLLAR(1000),
    TWENTY_DOLLAR(2000),
    FIFTY_DOLLAR(5000),
    HUNDRED_DOLLAR(10000);

    private final int valueInCents;


    Denomination(int valueInCents){
        this.valueInCents = valueInCents;
    }

    public int getValueInCents(){
        return getValueInCents();
    }

    public double getValueInDollars(){
        return valueInCents/100.0;
    }

}
