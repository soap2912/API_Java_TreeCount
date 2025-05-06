package com.example.Api_TreeCount_Fiap.Services.Util;

import java.util.regex.Pattern;

public class StringService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$"
    );

    public static boolean isEmail(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(input).matches();
    }

    // Verifica se o comprimento da string está dentro dos limites definidos
    // Verifica comprimento com min e max definidos
    public static boolean hasValidLength(String input, int minLength, int maxLength) {
        if (input == null) {
            return false;
        }
        int length = input.length();
        return length >= minLength && length <= maxLength;
    }

    public static boolean isStrongPassword(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        boolean hasUppercase = input.matches(".*[A-Z].*");
        boolean hasLowercase = input.matches(".*[a-z].*");
        boolean hasDigit = input.matches(".*\\d.*");
        boolean hasSpecialChar = input.matches(".*[^a-zA-Z0-9].*");

        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }

    public static boolean areEqual(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }

}
