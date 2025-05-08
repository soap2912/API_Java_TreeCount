package com.example.Api_TreeCount_Fiap.DTOs.UserDTO;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserDTO {

    private String password;

    private String confirmPassword;

    private String email;

    private String name;
}
