package com.example.muhammad.amotul.digicore.utils.generator;

import com.example.muhammad.amotul.digicore.model.AccountType;

public interface IAccountNumberGeneratorUtils {
    String generateAccountNum(int size, AccountType accountType);
}
