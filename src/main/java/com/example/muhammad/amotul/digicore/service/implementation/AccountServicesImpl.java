package com.example.muhammad.amotul.digicore.service.implementation;

import com.example.muhammad.amotul.digicore.AccountNameExistsException;
import com.example.muhammad.amotul.digicore.exceptions.AccountNotFoundException;
import com.example.muhammad.amotul.digicore.exceptions.AccountNumberExistsException;
import com.example.muhammad.amotul.digicore.exceptions.InvalidAcountNumber;
import com.example.muhammad.amotul.digicore.exceptions.InvalidAmountException;
import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.AccountType;
import com.example.muhammad.amotul.digicore.model.dto.request.AccountRegistrationRequestDTO;
import com.example.muhammad.amotul.digicore.repository.implementation.AccountRepositoryImpl;
import com.example.muhammad.amotul.digicore.service.interfaces.IAccountServices;
import com.example.muhammad.amotul.digicore.utils.generator.AccountNumberGeneratorUtilsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServicesImpl implements IAccountServices {
    @Autowired
    private AccountRepositoryImpl accountRepository;
    @Autowired
    private PasswordEncoder encoder;
    private final double INITIALDEPOSIT = 500.0;

    @Override
    public String addAccount(AccountRegistrationRequestDTO registrationRequestDTO) throws InvalidAcountNumber,
            AccountNumberExistsException, AccountNameExistsException, InvalidAmountException {
        Account account = new Account();
        String name = registrationRequestDTO.getAccountName();
        if(!checkIfAccountExist(name)){
            AccountType type = getAccountType(registrationRequestDTO.getAccountType());
            String accountNumber = new AccountNumberGeneratorUtilsImpl().generateAccountNum(accountRepository.getAccountsSize(), type);
            if(!checkIfAccountNumberExist(accountNumber)){
                if(accountNumber.length() == 10){
                    account.setAccountName(registrationRequestDTO.getAccountName());
                    account.setAccountNumber(accountNumber);
                    account.setPassword(encoder.encode(registrationRequestDTO.getPassword()));
                    account.setAccountType(type);
                    double deposit = registrationRequestDTO.getInitialDeposit();
                    if(deposit >= INITIALDEPOSIT){
                        account.setBalance(deposit);
                        accountRepository.addAccount(account);
                        return accountNumber;
                    }else{
                        throw new InvalidAmountException("Deposit for new Account must not be below N500.0");
                    }

                }else{
                    throw new InvalidAcountNumber("AccountNumber " +accountNumber + "is invalid");
                }
            }else{
                throw new AccountNumberExistsException("AccountNumber " +accountNumber + "already exists");
            }

        }else{
            throw new AccountNameExistsException("Account " + name + "already account exists");
        }


    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
        if(accountNumber != null || !accountNumber.isEmpty() || !accountNumber.isBlank()){
            Account account = accountRepository.getAccountByAccountNumber(accountNumber);
            if(account != null){
                return account;
            }else{
                throw new AccountNotFoundException("AccountNumber does not exist");
            }
        }
        return null;
    }

    @Override
    public boolean checkIfAccountExist(String accountName) {
        if(accountName != null || !accountName.isBlank() || !accountName.isEmpty()){
            return accountRepository.isAccountExists(accountName);
        }
        return false;
    }

    @Override
    public boolean checkIfAccountNumberExist(String accountNumber) {
        if(accountNumber != null || !accountNumber.isBlank() || !accountNumber.isEmpty()){
            return accountRepository.isAccountNumberExists(accountNumber);
        }
        return false;
    }

    private AccountType getAccountType(String type){
        if (type.equalsIgnoreCase("SAVINGS")){
            return AccountType.SAVINGS;
        }else{
            return AccountType.CURRENT;
        }
    }
}
