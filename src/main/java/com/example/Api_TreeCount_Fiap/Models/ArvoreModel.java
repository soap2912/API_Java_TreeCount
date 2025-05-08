package com.example.Api_TreeCount_Fiap.Models;

import com.example.Api_TreeCount_Fiap.DTOs.ArvoreDTO.CreateArvoreDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "arvore")
@Getter
@Setter
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

    // ✅ Construtor que aceita um CreateArvoreDTO
    public ArvoreModel(CreateArvoreDTO dto) {
        this.nomePopular = dto.getNomePopular();
        this.nomeCientifico = dto.getNomeCientifico();
        this.descricao = dto.getDescricao();
        this.formulaCarbono = dto.getFormulaCarbono();
        this.tipo = dto.getTipo();
    }

    // Construtor padrão obrigatório para JPA
    public ArvoreModel() {
    }



}
