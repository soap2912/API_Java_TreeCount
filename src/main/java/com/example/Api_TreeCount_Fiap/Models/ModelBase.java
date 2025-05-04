package com.example.Api_TreeCount_Fiap.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @Column(name = "deleted_at", nullable = false)
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

    // ===== [GETTERS] =====
    public Long getId() {
        return id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    // ===== [SETTERS] =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    // ===== [MÉTODO AUXILIAR PARA SOFT DELETE] =====
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}