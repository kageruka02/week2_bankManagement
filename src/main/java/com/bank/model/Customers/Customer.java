package com.bank.model.Customers;

import com.bank.model.Accounts.Account;

public  abstract class Customer {

    private String customerId;
    private String name;
    private int age;
    private String contact;
    private String address;
    public static int customerCounter = 0;



    public Customer(){

        setCustomerId();
    }

    public Customer(String name, int age, String contact, String address) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.address = address;
        setCustomerId();
    }

    //for retrieving customer from the file , it is already available
    public Customer(String customerId, String name, int age, String contact, String address) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.address = address;
    }

    public abstract String getCustomerType();

    public abstract String displayCustomerDetails();


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId() {
        Customer.customerCounter++;
        int n = Customer.customerCounter;
        String gen = "000"+ n;
        this.customerId= "CUS"+ gen.substring(gen.length()-3);
    }

    public static int getCustomerCounter() {
        return customerCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }
}
