package com.example.muhammad.amotul.digicore.model.dto.response;

import com.example.muhammad.amotul.digicore.model.Account;

public class GetAccountInfoResponseDTO {
    private int responseCode;
    private boolean success;
    private String message;
    private Account account;

    public GetAccountInfoResponseDTO(int responseCode, boolean success, String message, Account account) {
        this.responseCode = responseCode;
        this.success = success;
        this.message = message;
        this.account = account;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
