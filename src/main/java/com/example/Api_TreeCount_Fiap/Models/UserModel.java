package com.example.Api_TreeCount_Fiap.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter
@AttributeOverride(name = "id", column = @Column(name = "id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false))
public class UserModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
    private String id;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    // ===== [MÉTODOS DE CALLBACK] =====
    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAlteracao = LocalDateTime.now();
        this.deletedAt = null; // Garante que novos registros não sejam marcados como excluídos
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataAlteracao = LocalDateTime.now();
    }


    // ===== [MÉTODO AUXILIAR PARA SOFT DELETE] =====
    public void delete() {
        this.deletedAt = LocalDateTime.now();

        // Evita conflito de email único ao reutilizar o mesmo email no futuro
        if (this.email != null && this.id != null) {
            this.email = this.email + "-" + this.id;
        }
    }
}
