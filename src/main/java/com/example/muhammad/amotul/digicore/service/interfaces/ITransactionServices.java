package com.example.muhammad.amotul.digicore.service.interfaces;

import com.example.muhammad.amotul.digicore.exceptions.AccountNotFoundException;
import com.example.muhammad.amotul.digicore.exceptions.InsufficientFundException;
import com.example.muhammad.amotul.digicore.exceptions.InvalidAmountException;
import com.example.muhammad.amotul.digicore.model.Transaction;
import com.example.muhammad.amotul.digicore.model.dto.request.DepositRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.request.WithdrawalRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.response.GetAccountStatementResponseDTO;

import java.util.List;

public interface ITransactionServices {
    boolean depositToAccount(DepositRequestDTO depositRequestDTO) throws AccountNotFoundException, InvalidAmountException;
    boolean withdrawFromAccount(WithdrawalRequestDTO withdrawalRequestDTO) throws AccountNotFoundException, InsufficientFundException, InvalidAmountException;
    List<GetAccountStatementResponseDTO> getTransactionHistoryForAccount(String accountNumber);
}
