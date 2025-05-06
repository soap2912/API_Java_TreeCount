package com.example.Api_TreeCount_Fiap.DTOs.UserDTO;


import lombok.Getter;
import lombok.Setter;

public class CreateUserDTO {

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String confirmPassword;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String name;
}
