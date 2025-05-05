package com.example.Api_TreeCount_Fiap.DTOs.UserDTO;

import com.example.Api_TreeCount_Fiap.Models.ArvoreModel;

public class ArvoreResponse {
    private Long id;
    private String nomePopular;
    private String nomeCientifico;
    private String descricao;
    private String formulaCarbono;
    private String tipo;

    // Construtor
    public ArvoreResponse(ArvoreModel model) {
        this.id = model.getId();
        this.nomePopular = model.getNomePopular();
        this.nomeCientifico = model.getNomeCientifico();
        this.descricao = model.getDescricao();
        this.formulaCarbono = model.getFormulaCarbono();
        this.tipo = model.getTipo();
    }

    // Getters
    public Long getId() {
        return id;
    }

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
    public void setId(Long id) {
        this.id = id;
    }

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