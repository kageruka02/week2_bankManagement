## 🏦 Bank Management Application

## 📌 Overview
The Bank Management Application is a console-based Java application that simulates real-world banking operations such as account creation, deposits, withdrawals, and transaction tracking.  

This version of the project has been refactored into a Maven-based structure, with improved exception handling, input validation, and unit testing to better reflect real-world software development practices.

---

## 🚀 Key Improvements (Refactored Version)
- Converted to Maven project structure  
- Introduced custom exception hierarchy  
- Implemented fail-fast validation using exceptions  
- Added JUnit 5 unit testing  
- Improved code structure and separation of concerns  
- Refactored services and managers for maintainability  

---

```
src/
 ├── main/java/com/bank/
 │    ├── model/
 │    │    ├── Accounts/
 │    │    ├── Customers/
 │    │    ├── Transactions
 │    ├── service/
 │    ├── utils/
 │    ├── exceptions/
 │    └── management/
 │
 └── test/java/com/bank/
      ├── management/
      ├── model/
      └── exceptions/
```
---

## ⚙️ Features

### Core Functionality
- Create bank accounts (Savings & Checking)  
- Deposit and withdraw funds  
- Enforce business rules (minimum balance, overdraft)  
- View transaction history  
- CLI-based user interaction  

---

## 💰 Account Types
| Account Type      | Features |
|-------------------|----------|
| Savings Account   | • 3.5% interest rate • Minimum balance: $500 |
| Checking Account  | • Overdraft limit: $1000 • Monthly fee: $10 (waived for Premium customers) |

---

## 👤 Customer Types
| Customer Type | Benefits |
|---------------|----------|
| Regular       | Standard banking services |
| Premium       | • No monthly fee • Enhanced benefits |

---

## ⚠️ Exception Handling
The application uses a custom exception hierarchy:
- TransactionException (base class)  
- InsufficientFundsException  
- OverdraftExceededException  
- InvalidAmountException  
- InvalidAccountException  

---

## 🔥 Design Approach
- Validation methods throw exceptions instead of looping  
- Errors are handled centrally (fail-fast principle)  
- Improves readability and maintainability  

---

## 🧪 Testing
Unit tests are implemented using JUnit 5.

### Covered Areas
- Account operations (deposit, withdraw)  
- Exception scenarios (invalid input, overdraft, insufficient funds)  
- Account retrieval validation  

### Example Test
assertThrows(InvalidAccountException.class, () -> manager.findAccount("acc43567"));

---

## ▶️ How to Run
1. Clone the repository
   git clone https://github.com/kageruka02/week2_bankManagement
   cd week2_bankManagement

2. Run using Maven
   mvn exec:java "-Dexec.mainClass=com.bank.Main"   



---

## 🧠 OOP Principles Applied
| Principle      | Implementation |
|----------------|----------------|
| Encapsulation  | Private fields with getters/setters |
| Inheritance    | Account → SavingsAccount, CheckingAccount |
| Polymorphism   | Method overriding (withdraw, deposit) |
| Abstraction    | Abstract class Account, interface Transactable |
| Composition    | Manager classes handling collections |

---

## 🏗️ Architecture
| Layer            | Responsibility |
|------------------|----------------|
| CLI Layer        | User interaction |
| Service Layer    | Business logic |
| Validation Layer | Input validation |
| Domain Layer     | Models (Account, Customer) |
| Exception Layer  | Error handling |

---

## 🔗 Repository
👉 https://github.com/kageruka02/week2_bankManagement

---

## 👨‍💻 Author
- kageruka02  
- 📧 irumvaleon@gmail.com  

---

## 💬 Summary
This project demonstrates the transition from a basic Java application to a more structured and maintainable system by applying:
- Clean architecture principles  
- Exception-driven validation  
- Unit testing practices  
- Maven project organization  

It reflects both theoretical understanding and practical application of software engineering concepts.

🔗 Project Link: https://github.com/kageruka02/BankManagementApplication
