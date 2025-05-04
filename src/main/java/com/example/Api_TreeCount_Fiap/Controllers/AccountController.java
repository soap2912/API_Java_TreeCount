package com.example.Api_TreeCount_Fiap.Controllers;

import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.LoginDTO;
import com.example.Api_TreeCount_Fiap.DTOs.UserDTO.LoginResponseDTO;
import com.example.Api_TreeCount_Fiap.Services.UserService;
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

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody LoginDTO loginDto) {
return ResponseEntity.ok( "ble");
    }
}
