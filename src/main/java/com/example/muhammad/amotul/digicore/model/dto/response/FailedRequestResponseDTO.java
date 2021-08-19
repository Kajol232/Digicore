package com.example.muhammad.amotul.digicore.model.dto.response;

public class FailedRequestResponseDTO {
    private boolean success;
    private String message;

    public FailedRequestResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public FailedRequestResponseDTO() {

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
