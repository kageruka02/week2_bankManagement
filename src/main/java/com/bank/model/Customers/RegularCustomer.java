package com.bank.model.Customers;

public class RegularCustomer extends Customer{


    public RegularCustomer(String name, int age, String contact, String address){
        super(name, age, contact, address);
    }

    @Override
    public String getCustomerType() {
        return "Regular";
    }

    @Override
    public String displayCustomerDetails() {
        return "";
    }

}
