package com.example.muhammad.amotul.digicore.service.implementation;

import com.example.muhammad.amotul.digicore.exceptions.AccountNotFoundException;
import com.example.muhammad.amotul.digicore.exceptions.InsufficientFundException;
import com.example.muhammad.amotul.digicore.exceptions.InvalidAmountException;
import com.example.muhammad.amotul.digicore.model.Transaction;
import com.example.muhammad.amotul.digicore.model.TransactionType;
import com.example.muhammad.amotul.digicore.model.dto.request.DepositRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.request.WithdrawalRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.response.GetAccountStatementResponseDTO;
import com.example.muhammad.amotul.digicore.repository.implementation.AccountRepositoryImpl;
import com.example.muhammad.amotul.digicore.repository.implementation.TransactionRepositoryImpl;
import com.example.muhammad.amotul.digicore.service.interfaces.ITransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServicesImpl implements ITransactionServices {
    @Autowired
    private static AccountRepositoryImpl accountRepository;
    @Autowired
    private static TransactionRepositoryImpl transactionRepository;


    @Override
    public boolean depositToAccount(DepositRequestDTO depositRequestDTO) throws AccountNotFoundException, InvalidAmountException {
        double balance;
        if(depositRequestDTO != null){
            String accountNumber = depositRequestDTO.getAccountNumber();
            if(accountRepository.isAccountNumberExists(accountNumber)){
                double amount = depositRequestDTO.getAmount();
                if(amount >= 1.0 && amount <= 1000000.0){
                    balance = accountRepository.depositToAccount(accountNumber, amount);
                    if (balance > 0){
                        Transaction t = new Transaction();
                        t.setAccountNumber(accountNumber);
                        t.setBalance(balance);
                        t.setDescription(depositRequestDTO.getDescription());
                        t.setTransactionAmount(amount);
                        t.setTransactionType(TransactionType.DEPOSIT);
                        t.setTransactionDate(generateDate());
                        if(transactionRepository.addTransaction(t) != null){
                            return true;
                        }
                    }else{
                        throw new InvalidAmountException("Deposit amount must not less than N1.0 or greater than N1,000,000.0");
                    }
                }
            }else{
                throw new AccountNotFoundException("Accounts: " + accountNumber + " ,does not exists");
            }
        }
        return false;
    }

    @Override
    public boolean withdrawFromAccount(WithdrawalRequestDTO withdrawalRequestDTO) throws AccountNotFoundException, InsufficientFundException, InvalidAmountException {
        double balance;
        if(withdrawalRequestDTO != null){
            String accountNumber = withdrawalRequestDTO.getAccountNumber();
            if(accountRepository.isAccountNumberExists(accountNumber)){
                balance = accountRepository.getAccountByAccountNumber(accountNumber).getBalance();
                if(balance >= 500.0){
                    double amount = withdrawalRequestDTO.getAmount();
                    if(amount >= 1.0){
                        double newBal = accountRepository.withdrawFromAccount(accountNumber, amount);
                        if(newBal > 0){
                            Transaction t = new Transaction();
                            t.setTransactionDate(generateDate());
                            t.setTransactionType(TransactionType.WITHDRAWAL);
                            t.setTransactionAmount(amount);
                            t.setDescription(withdrawalRequestDTO.getDescription());
                            t.setBalance(newBal);
                            t.setAccountNumber(accountNumber);
                            if(transactionRepository.addTransaction(t) != null){
                                return true;
                            }
                        }
                    }else throw new InvalidAmountException("Withdrawal amount must no be less than N1.0");

                }else throw new InsufficientFundException("Insufficient Account balance ");

            }else{
                throw new AccountNotFoundException("Accounts: " + accountNumber + " ,does not exists");
            }
        }
        return false;
    }

    @Override
    public List<GetAccountStatementResponseDTO> getTransactionHistoryForAccount(String accountNumber) {
        List<GetAccountStatementResponseDTO> responseDTOList = new ArrayList<>();
        if(accountRepository.isAccountNumberExists(accountNumber)){
            List<Transaction> transactions = transactionRepository.getTransactionHistoryForAccount(accountNumber);
            for(Transaction t: transactions){
                responseDTOList.add(convertFromTransaction(t));
            }
            return responseDTOList;
        }
        return null;
    }

    private Date generateDate(){
        return new Timestamp(System.currentTimeMillis());
    }
    private GetAccountStatementResponseDTO convertFromTransaction(Transaction t){
        GetAccountStatementResponseDTO responseDTO = new GetAccountStatementResponseDTO();
        responseDTO.setAmount(t.getTransactionAmount());
        responseDTO.setBalance(t.getBalance());
        responseDTO.setDescription(t.getDescription());
        responseDTO.setTransactionDate(t.getTransactionDate());
        return responseDTO;
    }
}
