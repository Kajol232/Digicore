package com.example.muhammad.amotul.digicore.controller;

import com.example.muhammad.amotul.digicore.AccountNameExistsException;
import com.example.muhammad.amotul.digicore.exceptions.*;
import com.example.muhammad.amotul.digicore.model.Account;
import com.example.muhammad.amotul.digicore.model.dto.request.AccountLoginRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.request.AccountRegistrationRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.request.DepositRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.request.WithdrawalRequestDTO;
import com.example.muhammad.amotul.digicore.model.dto.response.*;
import com.example.muhammad.amotul.digicore.security.jwt.JWTFilter;
import com.example.muhammad.amotul.digicore.security.jwt.JWTProvider;
import com.example.muhammad.amotul.digicore.service.implementation.AccountServicesImpl;
import com.example.muhammad.amotul.digicore.service.implementation.AuthServiceImplementation;
import com.example.muhammad.amotul.digicore.service.implementation.TransactionServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
    @Autowired
    private static JWTProvider provider;
    @Autowired
    private static JWTFilter filter;
    @Autowired
    private static AccountServicesImpl accountServices;
    @Autowired
    private static TransactionServicesImpl transactionServices;
    @Autowired
    private static AuthServiceImplementation authService;

    @PostMapping(value = "/create_account", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> createAccount(@RequestBody AccountRegistrationRequestDTO registrationRequestDTO){
        AccountRegistrationResponseDTO responseDTO = new AccountRegistrationResponseDTO();
        FailedRequestResponseDTO failedResponse = new FailedRequestResponseDTO();
        if(!registrationRequestDTO.getPassword().equals(registrationRequestDTO.getConfirmPassword())){
            failedResponse.setSuccess(false);
            failedResponse.setMessage("Password Not Match");
        }else if(registrationRequestDTO.getPassword().isBlank() || registrationRequestDTO.getPassword().isEmpty() ||
                registrationRequestDTO.getPassword() == null){
            failedResponse.setSuccess(false);
            failedResponse.setMessage("Password cannot be blank");
        }else if(accountServices.checkIfAccountExist(registrationRequestDTO.getAccountName())){
            failedResponse.setSuccess(false);
            failedResponse.setMessage("Account already exist");
        }else{
            try {
                String accountNumber = accountServices.addAccount(registrationRequestDTO);
                if(accountNumber != null){
                    responseDTO.setCode(200);
                    responseDTO.setSuccess(true);
                    responseDTO.setMessage("Welcome, "+ registrationRequestDTO.getAccountName() +", " +
                            "Your accountNumber is " + accountNumber);
                    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
                }
            }catch (InvalidAmountException | AccountNameExistsException | AccountNumberExistsException | InvalidAcountNumber e) {
                failedResponse.setSuccess(false);
                failedResponse.setMessage(e.getMessage());
            }

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failedResponse);
    }

    @GetMapping(value = "/account_info", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getAccountInfo(@RequestBody String accountNumber){
        GetAccountInfoResponseDTO responseDTO = new GetAccountInfoResponseDTO();
        FailedRequestResponseDTO failedResponse = new FailedRequestResponseDTO();
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

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object > userLogin(@RequestBody AccountLoginRequestDTO requestDTO){
        AccountLoginResponseDTO responseDTO = new AccountLoginResponseDTO();
        if(requestDTO != null){
            try {
                Account account = authService.login(requestDTO);
                if(account != null){
                    String token = provider.generateToken(requestDTO.getAccountNumber());
                    responseDTO.setSuccess(true);
                    responseDTO.setAccessToken(token);
                    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
                }
            } catch (UnauthorizedUserAccount unauthorizedUserAccount) {
                responseDTO.setSuccess(false);
                responseDTO.setAccessToken(null);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new FailedRequestResponseDTO(false, unauthorizedUserAccount.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailedRequestResponseDTO(false, "Account does not exist"));

    }

}
