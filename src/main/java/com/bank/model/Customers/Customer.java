package com.bank.model.Customers;

public  abstract class Customer {

    private String customerId;
    private String name;
    private int age;
    private String contact;
    private String address;
    public static int customerCounter;

    public Customer(){

    }

    public Customer(String name, int age, String contact, String address) {
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

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
}
