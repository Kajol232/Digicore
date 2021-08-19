package com.example.muhammad.amotul.digicore.repository.implementation;

import com.example.muhammad.amotul.digicore.model.Transaction;
import com.example.muhammad.amotul.digicore.repository.interfaces.ITransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements ITransactionRepository {
    public static List<Transaction> transactions = new ArrayList<>();


    @Override
    public Transaction addTransaction(Transaction transaction) {
        if(transaction != null){
            transactions.add(transaction);
            return transaction;
        }
        return null;
    }

    @Override
    public List<Transaction> getTransactionHistoryForAccount(String accountNumber) {
        ArrayList<Transaction> accountTransaction = new ArrayList<>();
        for(Transaction t : transactions){
            if(t.getAccountNumber().equalsIgnoreCase(accountNumber)){
                accountTransaction.add(t);
            }
        }
        return accountTransaction.size() > 0 ? accountTransaction : null;
    }
}
