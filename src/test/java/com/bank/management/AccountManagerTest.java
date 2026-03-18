package com.bank.management;

import com.bank.exceptions.InvalidAccountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountManagerTest {

    AccountManager manager;

    @BeforeEach
    public void setup(){
        manager = new AccountManager();
    }

    @Test
    public void retrieveAccountWhichDoesNotExist(){
       assertThrows(InvalidAccountException.class, () -> manager.findAccount("acc43567") );
    }
}