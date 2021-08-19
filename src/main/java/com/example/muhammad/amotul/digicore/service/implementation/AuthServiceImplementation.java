package com.example.muhammad.amotul.digicore.service.implementation;

import com.example.muhammad.amotul.digicore.exceptions.AccountNotFoundException;
import com.example.muhammad.amotul.digicore.exceptions.UnauthorizedUserAccount;
import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.AccountType;
import com.example.muhammad.amotul.digicore.model.dto.request.AccountLoginRequestDTO;
import com.example.muhammad.amotul.digicore.repository.implementation.AccountRepositoryImpl;
import com.example.muhammad.amotul.digicore.service.interfaces.IAuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements IAuthServices {
    @Autowired
    private AccountRepositoryImpl accountRepository;

    @Autowired
    private PasswordEncoder encoder;
    @Override
    public Account findByAccountNumber(String accountNumber) {

        return accountRepository.getAccountByAccountNumber(accountNumber);
    }

    @Override
    public Account login(AccountLoginRequestDTO accountLogin) throws UnauthorizedUserAccount {
        Account account = findByAccountNumber(accountLogin.getAccountNumber());
        if(account != null){
            if(encoder.matches(accountLogin.getPassword(),account.getPassword())){
                return account;
            }else{
                throw new UnauthorizedUserAccount("Invalid Password");
            }
        }
        return null;
    }


}
