package com.example.Api_TreeCount_Fiap.DTOs.UserDTO;

import lombok.Getter;
import lombok.Setter;

public class CreateUserResponseDTO {

    @Getter @Setter
    private boolean success;

    @Getter @Setter
    private String message;

    @Getter @Setter
    private String id;
}
