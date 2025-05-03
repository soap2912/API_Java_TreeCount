package com.example.Api_TreeCount_Fiap.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users") // Boa prática: nome de tabela no plural
public class UserModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private String id;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String password;

    @Transient // Indica que NÃO é para persistir no banco
    private String confirmPassword;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // ===== [Construtores] =====
    public UserModel() {
        // Construtor padrão obrigatório para JPA
    }

    // ===== [Getters e Setters] =====
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}