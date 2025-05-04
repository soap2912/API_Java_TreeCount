package com.example.Api_TreeCount_Fiap.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "arvore")
public class ArvoreModel extends ModelBase {

    @Column(name = "nome_popular", nullable = false, length = 100)
    private String nomePopular;

    @Column(name = "nome_cientifico", nullable = false, length = 150)
    private String nomeCientifico;

    @Column(name="descricao", length = 255)
    private String descricao;

    @Column(name = "formula_carbono", length = 255)
    private String formulaCarbono;

    @Column(name="tipo",length = 50)
    private String tipo;

    // Getters e Setters

    public String getNomePopular() {
        return nomePopular;
    }

    public void setNomePopular(String nomePopular) {
        this.nomePopular = nomePopular;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFormulaCarbono() {
        return formulaCarbono;
    }

    public void setFormulaCarbono(String formulaCarbono) {
        this.formulaCarbono = formulaCarbono;
    }
}
