package com.example.Api_TreeCount_Fiap.Services;

import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.CreateUserDTO;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.CreateUserResponseDTO;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.LoginDTO;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.LoginResponseDTO;
import com.example.Api_TreeCount_Fiap.Models.UserModel;
import com.example.Api_TreeCount_Fiap.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CryptoService cryptoService;

    public LoginResponseDTO login(LoginDTO model) {

        Optional<UserModel> userOpt = userRepository.findByEmail(model.getUsername());

        if (userOpt.isEmpty()) {
            // Usuário não encontrado
            return new LoginResponseDTO(false, "Usuário ou senha inválidos", null);
        }

        UserModel user = userOpt.get();

        boolean passwordOk = BCrypt.checkpw(model.getPassword(), user.getPassword());


        //boolean passwordOk = cryptoService.verifyPassword(model.getPassword(), user.getPassword());

        if (!passwordOk) {
            return new LoginResponseDTO(false,"Usuário ou senha inválidos", null);
        }

        String token = new JwtService().generateToken(model.getUsername());

        return new LoginResponseDTO(true,"Login realizado com sucesso", token);
    }

    public CreateUserResponseDTO Create (CreateUserDTO model) {

        var resp = new CreateUserResponseDTO();

        try
        {
            Optional<UserModel> userOpt = userRepository.findByEmail(model.getEmail());

            if(userOpt.isPresent()){
                resp.setMessage("User já cadastrado");
                return resp;
            }

            UserModel user = new UserModel();
            user.setPassword(cryptoService.generateHashPassword(model.getPassword()));
            user.setConfirmPassword(cryptoService.generateHashPassword(model.getConfirmPassword()));
            user.setEmail(model.getEmail());
            user.setName(model.getName());

            UserModel savedUser = userRepository.save(user);

            resp.setSuccess(Boolean.TRUE);
            resp.setMessage("Usuario criado com sucesso");
            resp.setId(savedUser.getId());

            return resp;
        }
        catch(Exception e) {
            resp.setSuccess(Boolean.TRUE);
            resp.setMessage("Erro:" + e.getMessage());

            return resp;
        }
    }

    public void deleteUser(String id) {

        var user = userRepository.getById(id);

            userRepository.deleteById(id);
    }


}
