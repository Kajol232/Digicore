package com.example.muhammad.amotul.digicore.model.dto.request;

public class DepositRequestDTO {
    private String accountNumber;
    private Double amount;

    public DepositRequestDTO(String accountNumber, Double amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
