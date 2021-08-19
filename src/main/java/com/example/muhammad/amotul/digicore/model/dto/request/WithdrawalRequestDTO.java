package com.example.muhammad.amotul.digicore.model.dto.request;

public class WithdrawalRequestDTO {
    private String accountNumber;
    private String password;
    private Double amount;
    private String description;

    public WithdrawalRequestDTO(String accountNumber, String password, Double amount, String description) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.amount = amount;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
