package com.example.muhammad.amotul.digicore.model;

public class Account {
    private String accountNumber;
    private String accountName;
    private String password;
    private AccountType accountType;
    private double balance;

    public Account() {
    }

    public Account(String accountNumber, String accountName, String password, AccountType accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.password = password;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
