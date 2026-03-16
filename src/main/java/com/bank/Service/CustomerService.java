package com.bank.Service;

import com.bank.model.Customers.Customer;
import com.bank.model.Customers.PremiumCustomer;
import com.bank.model.Customers.RegularCustomer;
import com.bank.utils.CustomerInfo;

public class CustomerService {

    /**
     *
     * customerChoice
     *
     * @param customerChoice means 2 - regularCustomer , 1 - premiumCustomer
     * @param deposit the initial deposit which will help us know the type of customer
     * @param customerInfo contains the information required to create a customer
     * @return the customer created if all things are valid
     */

    public Customer createCustomer(int customerChoice, double deposit , CustomerInfo customerInfo){
        String customerName = customerInfo.getName();
        int age = customerInfo.getAge();
        String contact = customerInfo.getContact();
        String address = customerInfo.getAddress();
        if (customerChoice == 1 || deposit < 10000){
            return new RegularCustomer(customerName, age, contact, address);

        }
        else if (customerChoice==2 && deposit >= 10000 ){
            return new PremiumCustomer(customerName, age, contact, address);
        }
        else return null;
    }




}
