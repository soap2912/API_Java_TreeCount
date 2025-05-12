package com.example.Api_TreeCount_Fiap.DTOs.ArvoreDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateArvoreDTO {

    private String nomePopular;
    private String nomeCientifico;
    private String descricao;
    private String formulaCarbono;
    private String tipo;

    // Construtor padrão
    public UpdateArvoreDTO() {
    }

    // Pode ter um construtor com os campos necessários para atualização
    public UpdateArvoreDTO(String nomePopular, String nomeCientifico, String descricao, String formulaCarbono, String tipo) {
        this.nomePopular = nomePopular;
        this.nomeCientifico = nomeCientifico;
        this.descricao = descricao;
        this.formulaCarbono = formulaCarbono;
        this.tipo = tipo;
    }
}