# BankManagementApplication

## Overview
BankManagementApplication is a Java-based project that simulates basic banking operations such as account creation, deposits, withdrawals, and transaction management.  
It is designed for learning and demonstration purposes, with clear prompts for user interaction and well-commented code for complex logic.

---
## TABLE OF CONTENTS
- [Features](#FEATURES)
- [Account Types](#account-types)
- [Customer Types](#customer-types)
- [Getting Started](#getting-started)
- [Usage Guide](#usage-guide)
- [OOP Principles Applied](#oop-principles-applied)
- [Minimum Requirements](#minimum-requirements)

## FEATURES

### Core Functionality
- **Create Account** - Register new bank accounts for customers
- **View Accounts** - Display all accounts with their details
- **Process Transaction** - Deposit or withdraw money from accounts
- **View Transaction History** - Display transaction history for an account
- **Simple Menu Navigation** - Intuitive console interface

## ACCOUNT TYPES

| Account Type | Features |
|--------------|----------|
| **Savings Account** | • Earns 3.5% annual interest<br>• Minimum balance: $500<br>• Interest calculation available |
| **Checking Account** | • No interest<br>• Overdraft limit: $1000<br>• Monthly fee: $10 (waived for Premium customers) |

---

## CUSTOMER TYPES

| Customer Type | Benefits |
|---------------|----------|
| **Regular Customer** | Standard banking services |
| **Premium Customer** | • Higher transaction limits<br>• Waived monthly fees<br>• Minimum balance: $10,000<br>• Priority service |

## GETTING STARTED

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Git (for cloning the repository)

### Installation && USAGE GUIDE

1. **Clone the repository**
   ```bash
   git clone https://github.com/kageruka02/BankManagementApplication/tree/optimizedVersion
   cd BankManagementApplication
   cd src
   javac -d bin com\bank\Main.java (windows) or javac -d bin com/bank/Main.java(linux)
   java -cp bin com.bank.Main
2. **USAGE APPLICATION**
<img width="649" height="493" alt="image" src="https://github.com/user-attachments/assets/bad88574-786c-4c6a-9c01-3c9e318c0d58" />

### 1. Creating an Account
- Select option **1** from the main menu
- Enter customer details (name, age, contact, address)
- Choose customer type (Regular/Premium)
- Choose account type (Savings/Checking)
- Enter initial deposit amount
- System generates unique account number (ACC001, ACC002, etc.)
<img width="432" height="307" alt="image" src="https://github.com/user-attachments/assets/edfda4e0-8665-4516-8288-c2b34cd199b7" />

### 2. Viewing Accounts
- Select option **2** from the main menu  
- Displays all accounts with details:  
  - Account number  
  - Customer name and type  
  - Account type  
  - Current balance
<img width="948" height="646" alt="image" src="https://github.com/user-attachments/assets/4debf265-9bc1-4dcc-8ec0-9fb5be87955e" />


### 3. Processing Transactions
- Select option **3** from the main menu
- Enter account number  
- Choose transaction type (**Deposit/Withdraw**)  
- Enter transaction amount  
- System validates and updates balance  
- Confirmation message is displayed


### 4. Viewing Transaction History
- Select option **4** from the main menu  
- Enter account number  
- Displays all past transactions with:  
  - Date  
  - Type (Deposit/Withdraw)  
  - Amount  
  - Balance after transaction  

### 5. Exiting the Application
- Select option **5** from the main menu  
- Program terminates gracefully with a goodbye message

## OOP PRINCIPLES APPLIED

| Principle      | Implementation                          | Example                          |
|----------------|-----------------------------------------|----------------------------------|
| Encapsulation  | Private fields with public getters/setters | Account class fields             |
| Inheritance    | Parent-Child class relationships        | Account → SavingsAccount         |
| Polymorphism   | Method overriding                       | displayAccountDetails()          |
| Abstraction    | Abstract classes & interfaces           | Account (abstract), Transactable |
| Composition    | Manager classes containing collections  | AccountManager has Account[]     |

## MINIMUM REQUIREMENTS

- 📝 All 5 user stories working  
- 🔢 Static counters for ID generation  
- 📊 Array-based data management  
- ✅ Input validation implemented  
- 💰 Minimum balance enforcement for savings accounts  
- 💳 Overdraft limit enforcement for checking accounts  
- 📜 Transaction history tracking

## CONTACT
Project Maintainer - kageruka02

📧 Email: irumvaleon@gmail.com

🔗 Project Link: https://github.com/kageruka02/BankManagementApplication



