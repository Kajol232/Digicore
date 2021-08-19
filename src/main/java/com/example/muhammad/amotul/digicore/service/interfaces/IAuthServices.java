package com.example.muhammad.amotul.digicore.service.interfaces;

import com.example.muhammad.amotul.digicore.exceptions.UnauthorizedUserAccount;
import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.dto.request.AccountLoginRequestDTO;

public interface IAuthServices {
    Account findByAccountNumber(String accountNumber);
    Account login(AccountLoginRequestDTO accountLogin) throws UnauthorizedUserAccount;

}
