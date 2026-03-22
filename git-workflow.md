# 🌿 **Git Workflow**

## 📌 **Overview**

### **Main Branch**
- **`main`**
    - Contains **stable, finalized code**
    - Only **production-ready code** is merged here

### **Feature Branch**
- **`feature/error-handling`**
    - Used for implementing and improving:
        - **Input validation**
        - **Exception handling**
        - **Refactoring**

## 🔁 **Development Workflow**

### **1️⃣ Create a Feature Branch**
```bash
git checkout -b feature/<feature-name>
2️⃣ Implement Changes
✨ Testing and enhanced input validation

🔄 Code refactoring

🗑️ Remove unwanted file

3️⃣ Commit Changes
Examples:

feat: improve input validation with exception handling

refactor: clean up transaction service

fix: remove unwanted files

docs: update README

4️⃣ Merge Feature Branch
bash
git checkout main
git merge feature/error-handling
5️⃣ Clean Up
Removed .idea folder from repository

Used .gitignore to exclude IDE files