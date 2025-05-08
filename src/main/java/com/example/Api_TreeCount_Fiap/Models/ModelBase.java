package com.example.Api_TreeCount_Fiap.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    }
}