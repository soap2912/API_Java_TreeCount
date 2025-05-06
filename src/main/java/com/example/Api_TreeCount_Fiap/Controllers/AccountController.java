package com.example.Api_TreeCount_Fiap.Controllers;

import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.CreateUserDTO;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.CreateUserResponseDTO;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.LoginDTO;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.LoginResponseDTO;
import com.example.Api_TreeCount_Fiap.Services.UserService;
import com.example.Api_TreeCount_Fiap.Services.Util.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String GetAccount() {
        return "Is running";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDto) {

        try{
            if (loginDto.getPassword() == null || loginDto.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid Password"));
            }

            if (loginDto.getUsername() == null || loginDto.getUsername().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid Username"));
            }

            // Autentica o usuário
            LoginResponseDTO responseDto = userService.login(loginDto);

            Map<String, String> response = new HashMap<>();
            response.put("message", responseDto.getMessage());
            response.put("jwt_token", responseDto.getToken());

            if (!responseDto.isSuccess()) {
                return ResponseEntity.badRequest().body(response);
            }

            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateUserDTO model) {
        Map<String, String> response = new HashMap<>();
        CreateUserResponseDTO responseDTO = new CreateUserResponseDTO();

        // Verifica se todos os campos estão preenchidos corretamente
        if (!StringService.hasValidLength(model.getName(), 8, 256) ||
                !StringService.hasValidLength(model.getPassword(), 8, 36) ||
                !StringService.hasValidLength(model.getEmail(), 8, 256) ||
                !StringService.hasValidLength(model.getConfirmPassword(), 8, 36)) {

            responseDTO.setMessage("Necessário preencher todos os campos corretamente");
            responseDTO.setSuccess(Boolean.FALSE);
        }

        // Verifica se o email é válido
        else if (!StringService.isEmail(model.getEmail())) {
            responseDTO.setMessage("Email inválido");
            responseDTO.setSuccess(Boolean.FALSE);
        }

        // Verifica se a senha é forte
        else if (!StringService.isStrongPassword(model.getPassword())) {
            responseDTO.setMessage("A senha deve conter letra maiúscula, minúscula, número e caractere especial");
            responseDTO.setSuccess(Boolean.FALSE);
        }

        // Verifica se as senhas conferem
        else if (!StringService.areEqual(model.getPassword(), model.getConfirmPassword())) {
            responseDTO.setMessage("Senhas não conferem");
            responseDTO.setSuccess(Boolean.FALSE);
        }

        // Se houver erro, retorna bad request
        if (!responseDTO.isSuccess()) {
            response.put("message", responseDTO.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        // Cria o usuário
        responseDTO = userService.Create(model);

        response.put("message", responseDTO.getMessage());
        response.put("idUser", responseDTO.getId());

        return ResponseEntity.ok(response);
    }

}
