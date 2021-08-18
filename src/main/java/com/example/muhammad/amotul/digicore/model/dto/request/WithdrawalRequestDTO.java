package com.example.muhammad.amotul.digicore.model.dto.request;

public class WithdrawalRequestDTO {
    private String accountNumber;
    private String password;
    private Double amount;

    public WithdrawalRequestDTO(String accountNumber, String password, Double amount) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
