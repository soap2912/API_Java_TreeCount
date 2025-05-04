package com.example.Api_TreeCount_Fiap.Services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    private final BCryptPasswordEncoder passwordEncoder;

    public CryptoService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Método para gerar o hash da senha
    public String generateHashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    // (Opcional) Método para verificar se a senha em texto corresponde ao hash
    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }


}
