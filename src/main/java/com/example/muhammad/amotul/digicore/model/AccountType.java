package com.example.muhammad.amotul.digicore.model;

public enum AccountType {
    SAVINGS("100"),
    CURRENT("200");

    public final String value;

    private AccountType(String value){
        this.value = value;
    }
}
