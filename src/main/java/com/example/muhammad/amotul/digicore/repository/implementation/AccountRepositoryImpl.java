package com.example.muhammad.amotul.digicore.repository.implementation;

import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.AccountType;
import com.example.muhammad.amotul.digicore.repository.interfaces.IAccountRepository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountRepositoryImpl implements IAccountRepository {
    public static Map<String, Account> accounts = new HashMap<>();
    @Override
    public Account addAccount(Account account)  {
        if (account != null){
            accounts.put(account.getAccountNumber(), account);
            return account;
        }
        return null;
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accounts.getOrDefault(accountNumber, null);
    }

    @Override
    public boolean isAccountExists(String accountName) {
        for(Map.Entry<String, Account> account: accounts.entrySet()){
            if(account.getValue().getAccountName().equalsIgnoreCase(accountName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAccountNumberExists(String accountNumber) {
        for(Map.Entry<String, Account> account: accounts.entrySet()){
            if(account.getValue().getAccountNumber().equalsIgnoreCase(accountNumber)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public int getAccountsSize(){
        return accounts.size();
    }

    @Override
    public double depositToAccount(String accountNumber, double amount) {
        double balance;
        if(accountNumber != null){
            Account account = getAccountByAccountNumber(accountNumber);
            if (account != null){
                balance = account.getBalance() + amount;
                account.setBalance(balance);
                return balance;
            }
        }
        return 0;
    }

    @Override
    public double withdrawFromAccount(String accountNumber, double amount) {
        double balance;
        if(accountNumber != null){
            Account account = getAccountByAccountNumber(accountNumber);
            if (account != null){
                balance = account.getBalance() - amount;
                account.setBalance(balance);
                return balance;
            }
        }
        return 0;
    }

    private AccountType getAccountType(String type){
        if(type.equalsIgnoreCase("SAVINGS")){
            return AccountType.SAVINGS;
        }else{
            return AccountType.CURRENT;
        }
    }
}
