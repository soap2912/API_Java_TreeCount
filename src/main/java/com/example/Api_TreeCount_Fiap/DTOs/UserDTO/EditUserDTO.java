package com.example.Api_TreeCount_Fiap.DTOs.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserDTO {
    private String id;
    private String name;
    private String email;
    private String password;

    public EditUserDTO() {
    }

    public EditUserDTO(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
