package com.example.muhammad.amotul.digicore.service.implementation;

import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.AccountUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class AccountUserDetailsService implements UserDetailsService {
    @Autowired
    private AuthServiceImplementation authService;
    @Override
    public AccountUserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        Account account = authService.findByAccountNumber(accountNumber);
        return AccountUserDetails.getFromAccount(account);
    }
}
