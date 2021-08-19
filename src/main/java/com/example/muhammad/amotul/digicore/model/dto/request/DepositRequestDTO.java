package com.example.muhammad.amotul.digicore.model.dto.request;

public class DepositRequestDTO {
    private String accountNumber;
    private Double amount;
    private String description;

    public DepositRequestDTO(String accountNumber, Double amount, String description) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
