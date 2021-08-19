package com.example.muhammad.amotul.digicore.repository.interfaces;

import com.example.muhammad.amotul.digicore.model.Transaction;

import java.util.List;

public interface ITransactionRepository {
    Transaction addTransaction(Transaction transaction);
    List<Transaction> getTransactionHistoryForAccount(String accountNumber);
}
