package com.example.Api_TreeCount_Fiap.Controllers;

import com.example.Api_TreeCount_Fiap.DTOs.ResponseBaseDTO;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.*;
import com.example.Api_TreeCount_Fiap.Services.UserService;
import com.example.Api_TreeCount_Fiap.Services.Util.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        responseDTO.setSuccess(Boolean.TRUE);

        // Verifica se todos os campos estão preenchidos corretamente
        if (!StringService.hasValidLength(model.getName(), 2, 256) ||
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBaseDTO> delete(@PathVariable("id") String id) {
        ResponseBaseDTO responseDTO = new ResponseBaseDTO();

        try {
            if (id == null || id.isBlank()) {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Informe um ID válido");
                return ResponseEntity.badRequest().body(responseDTO);
            }

            // Chama o serviço que já retorna um ResponseBaseDTO
            responseDTO = userService.deleteUser(id);

            if (!responseDTO.isSuccess()) {
                // Se o usuário não foi encontrado ou houve falha no processo
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
            }

            // Sucesso na exclusão
            return ResponseEntity.noContent().build(); // HTTP 204

        } catch (Exception e) {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Erro ao excluir usuário: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<EditUserResponseDTO> edit(@PathVariable Long id, @RequestBody EditUserDTO model) {
        EditUserResponseDTO response = new EditUserResponseDTO();

        try {
            // Verificação se o ID foi enviado
            if (id == null) {
                response.setSuccess(false);
                response.setMessage("ID do usuário é obrigatório");
                return ResponseEntity.badRequest().body(response);
            }

            // Validação dos campos do DTO
            if (!StringService.hasValidLength(model.getName(), 2, 256) ||
                    !StringService.hasValidLength(model.getPassword(), 8, 36) ||
                    !StringService.hasValidLength(model.getEmail(), 8, 256)) {

                response.setSuccess(false);
                response.setMessage("Preencha todos os campos corretamente");
                return ResponseEntity.badRequest().body(response);
            }

            if (!StringService.isEmail(model.getEmail())) {
                response.setSuccess(false);
                response.setMessage("Email inválido");
                return ResponseEntity.badRequest().body(response);
            }

            if (!StringService.isStrongPassword(model.getPassword())) {
                response.setSuccess(false);
                response.setMessage("A senha deve conter letra maiúscula, minúscula, número e caractere especial");
                return ResponseEntity.badRequest().body(response);
            }

            // Chama o serviço para atualizar o usuário por ID
            ResponseBaseDTO updateResponse = userService.updateUserById(model); // - metodo por ID

            if (!updateResponse.isSuccess()) {
                response.setSuccess(false);
                response.setMessage(updateResponse.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.setSuccess(true);
            response.setMessage("Usuário atualizado com sucesso");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Erro ao editar usuário: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
