package com.example.muhammad.amotul.digicore.repositories;

import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.AccountType;
import com.example.muhammad.amotul.digicore.repository.implementation.AccountRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@DataJpaTest
class AccountRepositoryImplTest {
    @Autowired
    private AccountRepositoryImpl accountRepository;


    @BeforeEach
    void initUseCase(){
        Account account = new Account("1234567890", "Muhammad", "A@adeolu@123",
                        AccountType.CURRENT, 7000.0);
        accountRepository.addAccount(account);
    }

    @AfterEach
    void deleteAll(){

    }

    @Test
    void addAccount_success() {
        Account account = new Account("1237867890", "Muhammadul", "A@adeolu@123",
                AccountType.CURRENT, 7000.0);
        accountRepository.addAccount(account);
        AtomicInteger valid = new AtomicInteger();
        

    }

    @Test
    void getAccountByAccountNumber() {
    }

    @Test
    void isAccountExists() {
    }

    @Test
    void isAccountNumberExists() {
    }

    @Test
    void getAllAccounts() {
    }

    @Test
    void getAccountsSize() {
    }

    @Test
    void depositToAccount() {
    }

    @Test
    void withdrawFromAccount() {
    }
}