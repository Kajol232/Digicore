package com.example.muhammad.amotul.digicore.service.interfaces;

import com.example.muhammad.amotul.digicore.AccountNameExistsException;
import com.example.muhammad.amotul.digicore.exceptions.AccountNotFoundException;
import com.example.muhammad.amotul.digicore.exceptions.AccountNumberExistsException;
import com.example.muhammad.amotul.digicore.exceptions.InvalidAcountNumber;
import com.example.muhammad.amotul.digicore.exceptions.InvalidAmountException;
import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.dto.request.AccountRegistrationRequestDTO;

public interface IAccountServices {
    boolean addAccount(AccountRegistrationRequestDTO registrationRequestDTO) throws InvalidAcountNumber,
            AccountNumberExistsException, AccountNameExistsException, InvalidAmountException;
    Account getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException;
    boolean checkIfAccountExist(String accountName);
    boolean checkIfAccountNumberExist(String accountNumber);
}
