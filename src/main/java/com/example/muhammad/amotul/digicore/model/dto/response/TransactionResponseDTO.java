package com.example.muhammad.amotul.digicore.model.dto.response;

public class TransactionResponseDTO {
    private int responseCode;
    private boolean success;
    private String message;

    public TransactionResponseDTO(int responseCode, boolean success, String message) {
        this.responseCode = responseCode;
        this.success = success;
        this.message = message;
    }

    public TransactionResponseDTO() {

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
}
