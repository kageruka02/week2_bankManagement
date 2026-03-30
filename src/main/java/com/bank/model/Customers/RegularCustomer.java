package com.bank.model.Customers;

public class RegularCustomer extends Customer{


    public RegularCustomer(String name, int age, String contact, String address){
        super(name, age, contact, address);
    }

    //for customer retrieval from the file storage
    public RegularCustomer(String customerId, String name, int age, String contact, String address) {
        super(customerId, name, age, contact, address);
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
