package com.example.muhammad.amotul.digicore.model;

public class Account {
    private String accountNumber;
    private String accountName;
    private String password;

    public Account() {
    }

    public Account(String accountNumber, String accountName, String password) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.password = password;
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
}
