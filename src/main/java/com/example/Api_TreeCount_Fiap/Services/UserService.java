package com.example.Api_TreeCount_Fiap.Services;

import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.LoginDTO;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.LoginResponseDTO;
import com.example.Api_TreeCount_Fiap.Models.UserModel;
import com.example.Api_TreeCount_Fiap.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public LoginResponseDTO login(LoginDTO model) {

        // 1. Buscar usuário por e-mail (username)
        Optional<UserModel> userOpt = userRepository.findByEmail(model.getUsername());

        if (userOpt.isEmpty()) {
            // Usuário não encontrado
            return new LoginResponseDTO(false, "Usuário ou senha inválidos", null);
        }

        UserModel user = userOpt.get();

        // 2. Verificar se a senha bate com o hash
        boolean passwordOk = BCrypt.checkpw(model.getPassword(), user.getPassword());

        if (!passwordOk) {
            return new LoginResponseDTO(false,"Usuário ou senha inválidos", null);
        }

        // 3. Gerar token JWT (caso você tenha um JwtService separado)
        String token = new JwtService().generateToken(model.getUsername()); // você pode criar esse serviço depois

        return new LoginResponseDTO(true,"Login realizado com sucesso", token);
    }
}
