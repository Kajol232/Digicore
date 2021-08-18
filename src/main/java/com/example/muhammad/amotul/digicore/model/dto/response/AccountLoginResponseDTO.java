package com.example.muhammad.amotul.digicore.model.dto.response;

public class AccountLoginResponseDTO {
    private boolean success;
    private String accessToken;

    public AccountLoginResponseDTO(boolean success, String accessToken) {
        this.success = success;
        this.accessToken = accessToken;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
