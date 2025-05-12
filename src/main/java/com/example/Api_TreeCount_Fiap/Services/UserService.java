package com.example.Api_TreeCount_Fiap.Services;

import com.example.Api_TreeCount_Fiap.DTOs.ResponseBaseDTO;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.*;
import com.example.Api_TreeCount_Fiap.Models.UserModel;
import com.example.Api_TreeCount_Fiap.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

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

    public ResponseBaseDTO deleteUser(String id) {
        ResponseBaseDTO responseDTO = new ResponseBaseDTO();

        try {
            Optional<UserModel> userOpt = userRepository.findById(id);

            if (userOpt.isEmpty()) {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Usuário não encontrado para exclusão");
                return responseDTO;
            }

            UserModel user = userOpt.get();
            user.delete(); // soft delete (ajusta deletedAt e email)
            userRepository.save(user); // salva com email modificado e deletedAt preenchido

            responseDTO.setSuccess(true);
            responseDTO.setMessage("Usuário deletado com sucesso");

        } catch (Exception e) {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Erro ao tentar excluir o usuário: " + e.getMessage());
        }

        return responseDTO;
    }

    public UserModel getUserById(String id) {

        try{
            Optional<UserModel> userOpt = userRepository.findById(id);

            return userOpt.get();
        }catch (EntityNotFoundException e) {
            return null;
        }
    }

    public UserModel getUserByEmail(String email) {

        try{
            Optional<UserModel> userOpt = userRepository.findByEmail(email);

            return userOpt.get();
        }catch (EntityNotFoundException e) {
            return null;
        }
    }

    public boolean isAuthenticatedUser(String token, String idUser2) {

        Optional<UserModel> userOpt = getUserByJwtToken(token);

        if (!userOpt.isPresent()) return false;

        // ✅ Verifica se idUser2 é um UUID válido
        try {
            UUID.fromString(idUser2);
        } catch (IllegalArgumentException e) {
            return false; // id inválido
        }

        return userOpt.get().getId().equals(idUser2);
    }

    public  Optional<UserModel> getUserByJwtToken(String token) {

        String email = jwtService.extractUsername(token);

        Optional<UserModel> userOpt = userRepository.findByEmail(email);

        if (!userOpt.isPresent()) return null;

        return userOpt;

    }
    public List<UserModel> getUsersByIds(List<String> ids) {
        return userRepository.findAllById(ids);
    }

    //metodo para update buscando pelo e-mail, pois no DTO não tem id.
    public ResponseBaseDTO updateUserById(EditUserDTO dto) {
        ResponseBaseDTO response = new ResponseBaseDTO();

        try {
            Optional<UserModel> userOpt = userRepository.findById(dto.getId());

            if (userOpt.isEmpty()) {
                response.setSuccess(false);
                response.setMessage("Usuário não encontrado");
                return response;
            }

            UserModel user = userOpt.get();

            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword()); // Idealmente, criptografada

            userRepository.save(user);

            response.setSuccess(true);
            response.setMessage("Usuário atualizado com sucesso");

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Erro ao atualizar usuário: " + e.getMessage());
        }

        return response;
    }

}
