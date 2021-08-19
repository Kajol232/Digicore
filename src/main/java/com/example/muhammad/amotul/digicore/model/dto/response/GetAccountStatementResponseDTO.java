package com.example.muhammad.amotul.digicore.model.dto.response;

import java.util.List;

public class GetAccountStatementResponseDTO {
    private int code;
    private boolean success;
    private String message;

    private List<AccountStatementDTO> accountStatementDTOList;

    public GetAccountStatementResponseDTO() {
    }

    public GetAccountStatementResponseDTO(int code, boolean success, String message,
                                          List<AccountStatementDTO> accountStatementDTOList) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.accountStatementDTOList = accountStatementDTOList;
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

    public List<AccountStatementDTO> getAccountStatementDTOList() {
        return accountStatementDTOList;
    }

    public void setAccountStatementDTOList(List<AccountStatementDTO> accountStatementDTOList) {
        this.accountStatementDTOList = accountStatementDTOList;
    }
}
