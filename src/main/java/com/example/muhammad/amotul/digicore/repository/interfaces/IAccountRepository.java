package com.example.muhammad.amotul.digicore.repository.interfaces;

import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.dto.request.AccountRegistrationRequestDTO;

import java.util.Map;

public interface IAccountRepository {
    Account addAccount(AccountRegistrationRequestDTO registrationRequestDTO);
    Account getAccountByAccountNumber(String accountNumber);
    boolean isAccountExists(String accountName);
    boolean isAccountNumberExists(String accountNumber);
    Map<String, Account> getAllAccounts();

}
