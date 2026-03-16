package com.bank.model.Customers;

public class PremiumCustomer extends Customer{

    private double minimumBalance;

    public PremiumCustomer(String name, int age, String contact, String address){
        super(name, age, contact, address);
    }

    @Override
    public String getCustomerType() {
        return "Premium";
    }

    @Override
    public String displayCustomerDetails() {
        return "";
    }

    public Boolean hasWaivedFees(){
        return getCustomerType().equals("Premium");
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
}
