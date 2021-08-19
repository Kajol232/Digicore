package com.example.muhammad.amotul.digicore.controller;

import com.example.muhammad.amotul.digicore.AccountNameExistsException;
import com.example.muhammad.amotul.digicore.exceptions.AccountNotFoundException;
import com.example.muhammad.amotul.digicore.exceptions.AccountNumberExistsException;
import com.example.muhammad.amotul.digicore.exceptions.InvalidAcountNumber;
import com.example.muhammad.amotul.digicore.exceptions.InvalidAmountException;
import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.dto.request.AccountRegistrationRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.request.DepositRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.request.WithdrawalRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.response.*;
import com.example.muhammad.amotul.digicore.service.implementation.AccountServicesImpl;
import com.example.muhammad.amotul.digicore.service.implementation.TransactionServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
    @Autowired
    private static AccountServicesImpl accountServices;
    @Autowired
    private static TransactionServicesImpl transactionServices;

    @PostMapping(value = "/create_Account", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object createAccount(@RequestBody AccountRegistrationRequestDTO registrationRequestDTO){
        AccountRegistrationResponseDTO responseDTO = new AccountRegistrationResponseDTO();
        if(!registrationRequestDTO.getPassword().equals(registrationRequestDTO.getConfirmPassword())){
            responseDTO.setCode(400);
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Password Not Match");
        }else if(registrationRequestDTO.getPassword().isBlank() || registrationRequestDTO.getPassword().isEmpty() ||
        registrationRequestDTO.getPassword().equals(null)){
            responseDTO.setCode(400);
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Password cannot be blank");
        }else if(accountServices.checkIfAccountExist(registrationRequestDTO.getAccountName())){
            responseDTO.setCode(400);
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Account already exist");
        }else{
            try {
                String accountNumber = accountServices.addAccount(registrationRequestDTO);
                if(accountNumber != null){
                    responseDTO.setCode(200);
                    responseDTO.setSuccess(true);
                    responseDTO.setMessage("Welcome, "+ registrationRequestDTO.getAccountName() +", " +
                            "Your accountNumber is " + accountNumber);
                }
            }catch (InvalidAmountException | AccountNameExistsException | AccountNumberExistsException | InvalidAcountNumber e) {
                responseDTO.setCode(400);
                responseDTO.setSuccess(false);
                responseDTO.setMessage(e.getMessage());
            }

        }
        return responseDTO;
    }

    @GetMapping(value = "/account_info", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object getAccountInfo(@RequestBody String accountNumber){
        GetAccountInfoResponseDTO responseDTO = new GetAccountInfoResponseDTO();
        if(accountNumber != null || !accountNumber.isEmpty() || !accountNumber.isBlank()){
            try {
                Account account = accountServices.getAccountByAccountNumber(accountNumber);
                if(account != null){
                    responseDTO.setResponseCode(200);
                    responseDTO.setSuccess(true);
                    responseDTO.setMessage("Valid AccountNumber");
                    responseDTO.setAccount(account);
                }
            } catch (AccountNotFoundException e) {
                responseDTO.setResponseCode(200);
                responseDTO.setSuccess(true);
                responseDTO.setMessage(e.getMessage());
                responseDTO.setAccount(null);
            }

        }
        return responseDTO;
    }

    @GetMapping(value = "/account_statement", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetAccountStatementResponseDTO generateAccountStatement(@RequestBody String accountNumber){
        List<AccountStatementDTO> statements = new ArrayList<>();
        GetAccountStatementResponseDTO responseDTO = new GetAccountStatementResponseDTO();
        if(accountNumber != null || !accountNumber.isEmpty() || !accountNumber.isBlank()){
            try {
                statements =transactionServices.getTransactionHistoryForAccount(accountNumber);
                responseDTO.setCode(200);
                responseDTO.setSuccess(true);
                responseDTO.setMessage("Valid Account");
                responseDTO.setAccountStatementDTOList(statements);
            } catch (AccountNotFoundException e) {
                responseDTO.setCode(400);
                responseDTO.setSuccess(false);
                responseDTO.setMessage(e.getMessage());
                responseDTO.setAccountStatementDTOList(null);

            }
        }
        return responseDTO;
    }

    @PostMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object deposit(@RequestBody DepositRequestDTO depositRequestDTO){
        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        try {
            double bal = transactionServices.depositToAccount(depositRequestDTO);
            if(bal > 0){
                responseDTO.setResponseCode(200);
                responseDTO.setSuccess(true);
                responseDTO.setMessage("New Account Balance: " + bal);
            }
        } catch (AccountNotFoundException | InvalidAmountException e) {
            responseDTO.setResponseCode(400);
            responseDTO.setSuccess(false);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    @PostMapping(value = "/withdrawal", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object withdrawal(@RequestBody WithdrawalRequestDTO withdrawalRequestDTO){
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        String password = withdrawalRequestDTO.getPassword();
        return null;

    }

}
