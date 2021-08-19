package com.example.muhammad.amotul.digicore.service.interfaces;

import com.example.muhammad.amotul.digicore.exceptions.AccountNotFoundException;
import com.example.muhammad.amotul.digicore.exceptions.InsufficientFundException;
import com.example.muhammad.amotul.digicore.exceptions.InvalidAmountException;
import com.example.muhammad.amotul.digicore.model.dto.request.DepositRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.request.WithdrawalRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.response.AccountStatementDTO;

import java.util.List;

public interface ITransactionServices {
    double depositToAccount(DepositRequestDTO depositRequestDTO) throws AccountNotFoundException, InvalidAmountException;
    double withdrawFromAccount(WithdrawalRequestDTO withdrawalRequestDTO) throws AccountNotFoundException, InsufficientFundException, InvalidAmountException;
    List<AccountStatementDTO> getTransactionHistoryForAccount(String accountNumber) throws AccountNotFoundException;
}
