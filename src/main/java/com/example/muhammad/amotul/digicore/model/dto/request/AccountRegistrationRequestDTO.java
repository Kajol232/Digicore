package com.example.muhammad.amotul.digicore.model.dto.request;

import com.example.muhammad.amotul.digicore.utils.validator.ValidPassword;

public class AccountRegistrationRequestDTO {
    private String accountName;
    @ValidPassword
    private String password;
    private String confirmPassword;
    private Double initialDeposit;

    public AccountRegistrationRequestDTO(String accountName, String password, String confirmPassword, Double initialDeposit) {
        this.accountName = accountName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.initialDeposit = initialDeposit;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Double getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(Double initialDeposit) {
        this.initialDeposit = initialDeposit;
    }
}
