package com.example.muhammad.amotul.digicore.service.implementation;

import com.example.muhammad.amotul.digicore.exceptions.AccountNotFoundException;
import com.example.muhammad.amotul.digicore.exceptions.InsufficientFundException;
import com.example.muhammad.amotul.digicore.exceptions.InvalidAmountException;
import com.example.muhammad.amotul.digicore.exceptions.UnauthorizedUserAccount;
import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.Transaction;
import com.example.muhammad.amotul.digicore.model.TransactionType;
import com.example.muhammad.amotul.digicore.model.dto.request.DepositRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.request.WithdrawalRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.response.AccountStatementDTO;
import com.example.muhammad.amotul.digicore.repository.implementation.AccountRepositoryImpl;
import com.example.muhammad.amotul.digicore.repository.implementation.TransactionRepositoryImpl;
import com.example.muhammad.amotul.digicore.service.interfaces.ITransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServicesImpl implements ITransactionServices {
    @Autowired
    private AccountRepositoryImpl accountRepository;
    @Autowired
    private TransactionRepositoryImpl transactionRepository;
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public double depositToAccount(DepositRequestDTO depositRequestDTO) throws AccountNotFoundException, InvalidAmountException {
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
                            return balance;
                        }
                    }else{
                        throw new InvalidAmountException("Deposit amount must not less than N1.0 or greater than N1,000,000.0");
                    }
                }
            }else{
                throw new AccountNotFoundException("Accounts: " + accountNumber + " ,does not exists");
            }
        }
        return 0;
    }

    @Override
    public double withdrawFromAccount(WithdrawalRequestDTO withdrawalRequestDTO) throws AccountNotFoundException, InsufficientFundException, InvalidAmountException, UnauthorizedUserAccount {
        double balance;
        if(withdrawalRequestDTO != null){
            String accountNumber = withdrawalRequestDTO.getAccountNumber();
            if(accountRepository.isAccountNumberExists(accountNumber)){
                Account account = accountRepository.getAccountByAccountNumber(accountNumber);
                if(encoder.matches(withdrawalRequestDTO.getPassword(),account.getPassword())){
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
                                        return newBal;
                                    }
                                }
                            }else throw new InvalidAmountException("Withdrawal amount must no be less than N1.0");

                        }else throw new InsufficientFundException("Insufficient Account balance ");
                    }else{
                    throw new UnauthorizedUserAccount("Incorrect Password");
                }


            }else{
                throw new AccountNotFoundException("Accounts: " + accountNumber + " ,does not exists");
            }
        }
        return 0;
    }

    @Override
    public List<AccountStatementDTO> getTransactionHistoryForAccount(String accountNumber) throws AccountNotFoundException {
        List<AccountStatementDTO> responseDTOList = new ArrayList<>();
        if(accountRepository.isAccountNumberExists(accountNumber)){
            List<Transaction> transactions = transactionRepository.getTransactionHistoryForAccount(accountNumber);
            for(Transaction t: transactions){
                responseDTOList.add(convertFromTransaction(t));
            }
            return responseDTOList;
        }else{
            throw new AccountNotFoundException("Account Number does not exists");
        }

    }

    private Date generateDate(){
        return new Timestamp(System.currentTimeMillis());
    }
    private AccountStatementDTO convertFromTransaction(Transaction t){
        AccountStatementDTO responseDTO = new AccountStatementDTO();
        responseDTO.setAmount(t.getTransactionAmount());
        responseDTO.setBalance(t.getBalance());
        responseDTO.setDescription(t.getDescription());
        responseDTO.setTransactionDate(t.getTransactionDate());
        return responseDTO;
    }
}
