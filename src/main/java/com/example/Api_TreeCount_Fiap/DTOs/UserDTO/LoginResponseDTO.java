package com.example.Api_TreeCount_Fiap.DTOs.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private String token;
    private String message;
    private boolean success;

    public LoginResponseDTO(boolean success, String message, String token) {
        this.success = success;
        this.token = token;
        this.message = message;
    }
}
