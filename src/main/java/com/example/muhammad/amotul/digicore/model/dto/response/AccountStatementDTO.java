package com.example.muhammad.amotul.digicore.model.dto.response;

import com.example.muhammad.amotul.digicore.model.TransactionType;

import java.util.Date;

public class AccountStatementDTO {
    private Date transactionDate;
    private TransactionType transactionType;
    private String description;
    private double amount;
    private double balance;

    public AccountStatementDTO() {
    }

    public AccountStatementDTO(Date transactionDate, TransactionType transactionType, String description,
                               double amount, double balance) {
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.description = description;
        this.amount = amount;
        this.balance = balance;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
