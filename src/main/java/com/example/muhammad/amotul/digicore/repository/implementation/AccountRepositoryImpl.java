package com.example.muhammad.amotul.digicore.repository.implementation;

import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.dto.request.AccountRegistrationRequestDTO;
import com.example.muhammad.amotul.digicore.repository.interfaces.IAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountRepositoryImpl implements IAccountRepository {
    public static Map<String, Account> accounts = new HashMap<>();
    @Override
    public Account addAccount(AccountRegistrationRequestDTO registrationRequestDTO) {
        Account account = new Account();
        return null;
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return null;
    }

    @Override
    public boolean isAccountExists(String accountName) {
        return false;
    }

    @Override
    public boolean isAccountNumberExists(String accountNumber) {
        return false;
    }

    @Override
    public Map<String, Account> getAllAccounts() {
        return null;
    }
}
