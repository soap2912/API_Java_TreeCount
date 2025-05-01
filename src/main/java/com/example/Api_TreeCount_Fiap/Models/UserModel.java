package com.example.Api_TreeCount_Fiap.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

public class UserModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private String id;

    @Column(name = "password_hash")
    private String password;

    private String confirmPassword;

    private String email;

    private String name;

}
