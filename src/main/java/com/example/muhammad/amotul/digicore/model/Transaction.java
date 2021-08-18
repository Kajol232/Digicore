package com.example.muhammad.amotul.digicore.model;

import java.util.Date;

public class Transaction {
    private String accountNumber;
    private Date transactionDate;
    private String description;
    private double transactionAmount;
    private double balance;
    private TransactionType transactionType;

    public Transaction(String accountNumber, Date transactionDate, String description, double transactionAmount,
                       double balance, TransactionType transactionType) {
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
        this.description = description;
        this.transactionAmount = transactionAmount;
        this.balance = balance;
        this.transactionType = transactionType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
