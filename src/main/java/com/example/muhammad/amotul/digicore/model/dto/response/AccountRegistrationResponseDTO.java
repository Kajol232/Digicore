package com.example.muhammad.amotul.digicore.model.dto.response;

public class AccountRegistrationResponseDTO {
    private int code;
    private boolean success;
    private String message;

    public AccountRegistrationResponseDTO() {
    }

    public AccountRegistrationResponseDTO(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
