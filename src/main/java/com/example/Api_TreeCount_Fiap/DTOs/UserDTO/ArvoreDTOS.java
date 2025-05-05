package com.example.Api_TreeCount_Fiap.DTOs.UserDTO;

public class ArvoreDTO {
    private String nomePopular;
    private String nomeCientifico;
    private String descricao;
    private String formulaCarbono;
    private String tipo;

    // Getters
    public String getNomePopular() {
        return nomePopular;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFormulaCarbono() {
        return formulaCarbono;
    }

    public String getTipo() {
        return tipo;
    }

    // Setters
    public void setNomePopular(String nomePopular) {
        this.nomePopular = nomePopular;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setFormulaCarbono(String formulaCarbono) {
        this.formulaCarbono = formulaCarbono;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}