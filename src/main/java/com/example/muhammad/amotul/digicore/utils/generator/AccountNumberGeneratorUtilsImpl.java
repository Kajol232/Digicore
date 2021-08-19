package com.example.muhammad.amotul.digicore.utils.generator;

import com.example.muhammad.amotul.digicore.model.AccountType;

import java.util.Random;

public class AccountNumberGeneratorUtilsImpl implements IAccountNumberGeneratorUtils{
    @Override
    public String generateAccountNum(int size, AccountType accountType) {
        String accountNumber = accountType.value;
        int length = String.valueOf(size).length();
        String num = generateRandomDigits(7 - length);
        if(num.length() == length){
            accountNumber += num + size;
            return accountNumber;
        }

        return null;
    }

    private static String generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return String.valueOf(m + new Random().nextInt(9 * m));
    }


}
