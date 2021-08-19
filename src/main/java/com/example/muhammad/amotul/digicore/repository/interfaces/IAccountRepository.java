package com.example.muhammad.amotul.digicore.repository.interfaces;

import com.example.muhammad.amotul.digicore.model.Account;


import java.util.Map;

public interface IAccountRepository {
    Account addAccount(Account account);
    Account getAccountByAccountNumber(String accountNumber);
    boolean isAccountExists(String accountName);
    boolean isAccountNumberExists(String accountNumber);
    Map<String, Account> getAllAccounts();
    int getAccountsSize();
    double depositToAccount(String accountNumber, double amount);
    double withdrawFromAccount(String accountNumber, double amount);

}
