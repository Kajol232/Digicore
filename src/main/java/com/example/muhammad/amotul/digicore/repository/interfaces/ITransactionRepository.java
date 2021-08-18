package com.example.muhammad.amotul.digicore.repository.interfaces;

import com.example.muhammad.amotul.digicore.model.Transaction;

import java.util.List;

public interface ITransactionRepository {
    double depositToSelfAccount(String accountNumber, double amount);
    double depositToOtherAccount(String creditAccountNumber, String debitAccountNumber, double amount);
    double withdrawFromAccount(String accountNumber, double amount);
    List<Transaction> getTransactionHistoryForAccount(String accountNumber);
}
