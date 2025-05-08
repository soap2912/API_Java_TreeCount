package com.example.Api_TreeCount_Fiap.Services.Util;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
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

    public static boolean hasEmptyStringFields(Object objeto) {
        if (objeto == null) return false;

        Class<?> clazz = objeto.getClass();
        Field[] campos = clazz.getDeclaredFields();

        try {
            for (Field campo : campos) {
                if (campo.getType().equals(String.class)) {
                    campo.setAccessible(true);
                    String valor = (String) campo.get(objeto);
                    if (valor == null || valor.trim().isEmpty()) {
                        return true;  // Retorna true se encontrar um campo vazio
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }

        return false;  // Retorna false se não encontrar nenhum campo vazio
    }


    public static boolean isNullOrWhiteSpace(String str) {
        if (str == null) {
            return true; // Considera null como "vazio"
        }
        return str.trim().isEmpty(); // Remove espaços em branco no início e no final e verifica se a string resultante é vazia
    }

    public static Map<String, String> toMapStringString(Object obj) {
        Map<String, String> resultado = new HashMap<>();

        if (obj == null) {
            return resultado;
        }

        Class<?> clazz = obj.getClass();

        // Iterar pela hierarquia de classes
        while (clazz != null && clazz != Object.class) {
            Field[] campos = clazz.getDeclaredFields();

            for (Field campo : campos) {
                campo.setAccessible(true);
                try {
                    Object valor = campo.get(obj);

                    // Só adiciona ao mapa se o valor não for nulo
                    if (valor != null) {
                        resultado.put(campo.getName(), valor.toString());
                    }
                } catch (IllegalAccessException e) {
                    // Log, se necessário
                    resultado.put(campo.getName(), "ERRO_ACESSO");
                }
            }

            clazz = clazz.getSuperclass(); // Subir para a classe pai
        }

        return resultado;
    }
}
