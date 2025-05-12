package com.example.Api_TreeCount_Fiap.DTOs.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EditUserResponseDTO {
    private boolean success;
    private String message;

    // Construtor padrão
    public EditUserResponseDTO() {
    }

    // Construtor com parâmetros
    public EditUserResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
